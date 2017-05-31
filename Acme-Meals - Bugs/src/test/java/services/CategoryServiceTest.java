package services;

import java.text.ParseException;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Category;
import forms.CategoryForm;


import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class CategoryServiceTest extends AbstractTest{
	
	@Autowired
	private CategoryService		categoryService;
	
	//Test
	/*Manage his categories, which includes creating, listing, editing and deleting them.
	 * 
	 * Primero vamos a crear una categoria
	 */
	@Test
	public void driverCreate() throws ParseException {
		Object testingData[][] = {
		{//Manager con todos los datos correctos
			"manager1", "Prueba",null},
		{//Usuario creando una category
			"user1", "Prueba",IllegalArgumentException.class},
		{//Usuario sin autenticar creando una category
			null, "Prueba",IllegalArgumentException.class},
};

		
		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
		protected void templateCreate(String username,String name, Class<?> expected) throws ParseException {
		
			Class<?> caught = null;
			
			try {
				authenticate(username); // Iniciamos sesión con el usuario
				
				CategoryForm categoryForm;
				categoryForm = categoryService.generate();
				
				categoryForm.setName(name);
				
				Category category = categoryService.reconstruct(categoryForm, null);
				Assert.isTrue(category != null);
				categoryService.save(category);

				unauthenticate();
			} catch (Throwable oops) {
				caught = oops.getClass();
			}
			checkExceptions(expected, caught);
		}
		//Test
		/*Manage his categories, which includes creating, listing, editing and deleting them.
		 * 
		 * Edicion de una categoria
		 */
		@Test
		public void driverEdit() throws ParseException {
			Object testingData[][] = {
					
				{// Editar una category propia
					"manager1", 112, "Prueba",null},
				{// Editar una category de otro manager
					"manager2", 112, "Prueba", IllegalArgumentException.class},
				{// Editar una category como usuario
					"user1", 112, "Prueba", IllegalArgumentException.class}
			};
			
			for (int i = 0; i < testingData.length; i++)
				templateEdit((String) testingData[i][0], (int) testingData[i][1],(String) testingData[i][2],(Class<?>) testingData[i][3]);
		}
		protected void templateEdit(String username, int categoryId, String name, Class<?> expected) throws ParseException {
			
			Class<?> caught = null;
			
			try {
				authenticate(username); // Iniciamos sesión con el usuario
				Category s;
				Category r= categoryService.findOne(categoryId);
				r.setName(name);
				s = categoryService.save(r);
				Assert.isTrue(s!=null);
				Assert.isTrue(s.getName().equals(name));

				unauthenticate();
			} catch (Throwable oops) {
				caught = oops.getClass();
			}
			checkExceptions(expected, caught);
		}
		
		//Test
			/*Manage his categories, which includes creating, listing, editing and deleting them.
			 * 
			 * Listar categorias creadas por un manager
			 */
			@Test
			public void driverList() throws ParseException {
				Object testingData[][] = {
						
					{// Listar categorias creadas por un manager
						"manager1",null},
					{//Listar categorias siendo un usuario no autenticado
						null, IllegalArgumentException.class},
					{//Listar categorias siendo un usuario 
						"user1", IllegalArgumentException.class}
				};
				
				for (int i = 0; i < testingData.length; i++)
					templateList((String) testingData[i][0],(Class<?>) testingData[i][1]);
			}
			protected void templateList(String username,Class<?> expected) throws ParseException {
				
				Class<?> caught = null;
				
				try {
					authenticate(username); // Iniciamos sesión con el usuario
					Collection<Category> r= categoryService.findCategoryByManager();
					Assert.notNull(r);
					unauthenticate();
				} catch (Throwable oops) {
					caught = oops.getClass();
				}
				checkExceptions(expected, caught);
			}

			//Test
			/*Manage his categories, which includes creating, listing, editing and deleting them.
			 * 
			 * Delete category
			 */
			@Test
			public void driverDelete() throws ParseException {
				Object testingData[][] = {
						
					{// Borrar una category propia
						"manager1", 112,null},
					{// Borrar una category de otro manager
						"manager2", 112, IllegalArgumentException.class},
					{// Borrar una category como usuario
						"user1", 112, IllegalArgumentException.class}
				};
				
				for (int i = 0; i < testingData.length; i++)
					templateDelete((String) testingData[i][0], (int) testingData[i][1],(Class<?>) testingData[i][2]);
			}
			protected void templateDelete(String username, int categoryId, Class<?> expected) throws ParseException {
				
				Class<?> caught = null;
				
				try {
					authenticate(username); // Iniciamos sesión con el usuario
					
					Category r= categoryService.findOne(categoryId);
					categoryService.delete(r);
					

					unauthenticate();
				} catch (Throwable oops) {
					caught = oops.getClass();
				}
				checkExceptions(expected, caught);
			}

}
