/*package aristoweb;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import aristoweb.model.Post;
import aristoweb.repository.PostRepository;

	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(classes = { InfrastructureContextConfiguration.class, TestDataContextConfiguration.class })
	@Transactional
	public class PostControllerTests {

		@Autowired
		private PostRepository bookRepository;
		@PersistenceContext
		private EntityManager entityManager;

		private Post _post;
		//private Category category;

		@Before
		public void setupData() {
			EntityBuilderManager.setEntityManager(entityManager);

			 _post.setArticleContent("this is article");
		        _post.setArticleTitle("atricle");
		        _post.setPostCommentCount(4);
		        _post.setPostContent("ththththththththththththth");
		        _post.setPostDate(new Date());
		        _post.setPostHasArticle((byte)1);
		        _post.setPostLikeCount(500);
		        _post.setPostStatus("New");
		        _post.setPostType("Info");
		}

		@After
		public void tearDown() {
			EntityBuilderManager.clearEntityManager();
		}

		@Test
		public void testFindById() {
			entityManager.flush();
			Book book = bookRepository.findById(this.book.getId());
			assertEquals(this.book.getAuthor(), book.getAuthor());
			assertEquals(this.book.getDescription(), book.getDescription());
			assertEquals(this.book.getIsbn(), book.getIsbn());
		}

		@Test
		public void testFindByCategory() {
			setupData();
			List<Book> books = bookRepository.findByCategory(category);
			assertEquals(1, books.size());

			for (Book book : books) {
				assertEquals(this.book.getCategory().getId(), category.getId());
				assertEquals(this.book.getAuthor(), book.getAuthor());
				assertEquals(this.book.getDescription(), book.getDescription());
				assertEquals(this.book.getIsbn(), book.getIsbn());
			}
		}

		@Test
		@Rollback(true)
		public void testStoreBook() {
			Book book = new BookBuilder() {
				{
					description("Something");
					author("JohnDoe");
					title("John Doe's life");
					isbn("1234567890123");
					category(category);
				}
			}.build();

			bookRepository.storeBook(book);

			// Explicitly flush so any CUD query that is left behind is send to the database before rolling back
			entityManager.flush();
			
			Book book1 = bookRepository.findById(book.getId());
			
			assertEquals(book1.getAuthor(), book.getAuthor());
			assertEquals(book1.getDescription(), book.getDescription());
			assertEquals(book1.getIsbn(), book.getIsbn());
		}
		
		@Test
	    public void testFindBooks() {
			BookSearchCriteria bookSearchCriteria = new BookSearchCriteria();
			bookSearchCriteria.setTitle(book.getTitle());
			List<Book> books = bookRepository.findBooks(bookSearchCriteria);
			
			for (Book book : books) {
				assertEquals(this.book.getCategory().getId(), category.getId());
				assertEquals(this.book.getAuthor(), book.getAuthor());
				assertEquals(this.book.getDescription(), book.getDescription());
				assertEquals(this.book.getIsbn(), book.getIsbn());
			}
	    }

		@Test
	    public void testFindRandom() {
			List<Book> books = bookRepository.findRandom(0);
			assertTrue(books.size() != 0);
	    }
}
*/