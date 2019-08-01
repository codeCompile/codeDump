package aristoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import aristoweb.dao.Employee;
import aristoweb.dao.EmployeeDAO;
import aristoweb.lib.BlogObject;
import aristoweb.lib.PostInfo;
import aristoweb.model.Post;
import aristoweb.repository.PostRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> postsList(Model model){
        model.addAttribute("posts", postRepository.findAll());
       // model.addAttribute("orders", orderRepository.findAll());
        //return "posts";
        List<Post> aPosts = postRepository.findAll();
    	if (aPosts.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Post>>(aPosts, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/postsInfo", method = RequestMethod.GET)
    public ResponseEntity<List<PostInfo>> postsInfoList(Model model){
        model.addAttribute("posts", postRepository.findAll());
       // model.addAttribute("orders", orderRepository.findAll());
        //return "posts";
        List<Post> aPosts = postRepository.findAll();
        List<PostInfo> lstPinfo = new ArrayList<PostInfo>();
        for(Post i : aPosts)
        {
        	PostInfo pinfo = new PostInfo();
        	pinfo.post_ID = i.getPost_ID();
        	pinfo.articleTitle = i.getArticleTitle();
        	lstPinfo.add(pinfo);
        }
    	if (aPosts.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<PostInfo>>(lstPinfo, HttpStatus.OK);
    }
    
    /*
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public Response getEmployee() {
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> employees = dao.getEmployees();
        //return Response.ok(employees, MediaType.APPLICATION_JSON_TYPE).build();

        return Response.ok().entity(new GenericEntity<List<Employee>>(employees) {}).build();
        //return "Count is : " + employees.size();
    }*/
    
    @RequestMapping(value="/createfixpost", method = RequestMethod.GET)
    @ResponseBody
    public String saveFixPost()
    //@RequestParam String firstName, @RequestParam String lastName, @RequestParam(value="productIds[]") Long[] productIds)
    {

        Post _post = new Post();
        _post.setArticleContent("this is article");
        _post.setArticleTitle("atricle");
        _post.setPostCommentCount(4);
        _post.setPostContent("ththththththththththththth");
        _post.setPostDate(new Date());
        _post.setPostHasArticle((byte)1);
        _post.setPostLikeCount(500);
        _post.setPostStatus("New");
        _post.setPostType("Info");
        
        postRepository.save(_post);
        return "New post fix created with id: " + _post.getPost_ID().toString();
    }
    
    @RequestMapping(value="/getpostbyid", method = RequestMethod.GET)
    @ResponseBody
    public Post getPostById(@RequestParam int postId)
    {
    	List<Post> allPost = postRepository.findAll();
    	for(Post pst : allPost)
    	{
    		if(pst.getPost_ID() == postId)
    		{
    			return pst;
    		}
    	}
        //Post _post = new Post();
        return null;
    }
    
    @RequestMapping(value="/createpost", method = RequestMethod.POST)
    @ResponseBody
    public String savePost( @RequestBody BlogObject blogObject ) throws ParseException
    {
    	String strRetunMsg = "Didn't do anything.";
    	if(("NEW").equals(blogObject.postStatus.toUpperCase()))
    	{
         Post _post = new Post();
         TimeZone tz = TimeZone.getTimeZone("UTC");
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
         df.setTimeZone(tz);
         String nowAsISO = df.format(blogObject.postDate);

         System.out.println(nowAsISO);

         DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
       //nowAsISO = "2013-05-31T00:00:00Z";
         Date finalResult = df1.parse(nowAsISO);

         System.out.println("now as iso " + blogObject.postDate + " final date" + finalResult);
         
         
        _post.setArticleContent(blogObject.articleContent);
        _post.setArticleTitle(blogObject.articleTitle);
        _post.setPostCommentCount(4);
        _post.setPostContent(blogObject.postContent);
        // DateTime dt = new DateTime(blogObject.postDate);
        _post.setPostDate(blogObject.postDate);
        //_post.setPostDate(new Date());

        _post.setPostHasArticle((byte)1);
        _post.setPostLikeCount(500);
        _post.setPostStatus(blogObject.postStatus);
        _post.setPostType(blogObject.postType);
        //postUserName
       
        postRepository.save(_post);
        strRetunMsg ="New post created with id: " + _post.getPost_ID().toString(); //blogObject.articleContent + blogObject.articleTitle  + blogObject.postContent + _post.getPost_ID().toString();
       }
    	else if(blogObject.postStatus != null)
    	{
    		Post existingPost = null;
    		/*List<Post> allPost = postRepository.findAll();
        	for(Post pst : allPost)
        	{
        		if(pst.getPost_ID() == blogObject.post_ID)
        		{
        			existingPost = pst;
        			break;
        		}
        	}*/
        	
        	existingPost = postRepository.findOne(blogObject.post_ID);
        	
        	if(existingPost != null)
        	{
        		if( "DELETE".equals(blogObject.postStatus.toUpperCase()))
        		{
        			postRepository.delete(existingPost);
        			strRetunMsg = String.format("Blog with id: %s deleted" ,  blogObject.post_ID);
        		}
        		else {
        			existingPost.setArticleContent(blogObject.articleContent);
        			existingPost.setArticleTitle(blogObject.articleTitle);
        			existingPost.setPostContent(blogObject.postContent);
           			existingPost.setPostDate(blogObject.postDate);
        			existingPost.setPostHasArticle((byte)1);
        			existingPost.setPostCommentCount(existingPost.getPostCommentCount() + 1);
        			existingPost.setPostLikeCount(existingPost.getPostLikeCount() + 1);
        			existingPost.setPostStatus(blogObject.postStatus);
        		    existingPost.setPostType(blogObject.postType);
        		    postRepository.save(existingPost);
        		    strRetunMsg = String.format("Blog with id: %s updated" ,  blogObject.post_ID);
        		}
        	}
    	}
    	
    	 System.out.println(strRetunMsg);
         return strRetunMsg; //new ResponseEntity<String>(str,HttpStatus.OK);
     	
    }
    
    @RequestMapping(value = "/removepost", method = RequestMethod.POST)
    @ResponseBody
    public String removePost(@RequestParam Long Id){
        postRepository.delete(Id);
        return Id.toString();
    }
}