package aristoweb.main;


import java.util.HashMap;
import java.util.List;
 
import aristoweb.dao.Employee;
import aristoweb.dao.EmployeeDAO;
 
@Path("employees")
public class MyResource {
 
    @GET
    //@Produces("application/json")
    @Produces(MediaType.TEXT_PLAIN)
    public String getEmployee() {
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> employees = dao.getEmployees();
        //return Response.ok(employees, MediaType.APPLICATION_JSON_TYPE).build();

        //Response.ok().entity(new GenericEntity<List<Employee>>(employees) {}).build();
        return "Count is : " + employees.size();
    }
    
/*
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello World !!";
    }
 */
    
    @POST
    @Path("/create")
    @Consumes("application/json")
    public Response addEmployee(Employee emp){
        emp.setName(emp.getName());
        emp.setAge(emp.getAge());
                
        EmployeeDAO dao = new EmployeeDAO();
        dao.addEmployee(emp);
        
        return Response.ok().build();
    }
    
    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updateEmployee(@PathParam("id") int id, Employee emp){
        EmployeeDAO dao = new EmployeeDAO();
        int count = dao.updateEmployee(id, emp);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Consumes("application/json")
    public Response deleteEmployee(@PathParam("id") int id){
        EmployeeDAO dao = new EmployeeDAO();
        int count = dao.deleteEmployee(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}
