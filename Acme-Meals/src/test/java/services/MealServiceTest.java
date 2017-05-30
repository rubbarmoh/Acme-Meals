package services;

import java.text.ParseException;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Category;
import domain.Meal;

import forms.MealForm;


import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class MealServiceTest extends AbstractTest {
	// The SUT -----------------------------------------------

		@Autowired
		private MealService			mealService;
		
		@Autowired
		private CategoryService		categoryService;
		
	

		// Other services

		

		// Tests --------------------------------------------------
		/*Manage the meals of his/her restaurant, which includes creating, 
		 * listing, editing and deleting them. A deleted meal will be not 
		 * available to order, but it still stored in the system for older orders.
		 * 
		 * Primero vamos a crear un meal.
		 */
		@Test
		public void driverCreate() throws ParseException {
			Object testingData[][] = {
			{//Manager con todos los datos correctos
				"manager1", "Plato", "Descripcion", 5.0,false,106,112,null},
			{//Ususario no autenticado
				null, "Plato", "Descripcion", 5.0,false,106,112,IllegalArgumentException.class},
			{//Ususario 
				"user1", "Plato", "Descripcion", 5.0,false,106,112,IllegalArgumentException.class},
			
			};

		
		for (int i = 0; i < testingData.length; i++)
			templateCreate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Double) testingData[i][3], (Boolean) testingData[i][4], (Integer) testingData[i][5], (Integer) testingData[i][6],(Class<?>) testingData[i][7]);
	}
		protected void templateCreate(String username,String title, String description, Double price,Boolean erased, Integer rest,Integer cat,Class<?> expected) throws ParseException {
		
			Class<?> caught = null;
			
			try {
				authenticate(username); // Iniciamos sesión con el usuario
				Category category=categoryService.findOne(cat);
				MealForm mealForm;
				mealForm = mealService.generateForm();
				
				mealForm.setTitle(title);
				mealForm.setDescription(description);
				mealForm.setPrice(price);
				mealForm.setCategory(category);
				mealForm.setrId(rest);
				
				Meal meal = mealService.reconstruct(mealForm, null);
				Assert.isTrue(meal != null);
				mealService.save(meal);

				unauthenticate();
			} catch (Throwable oops) {
				caught = oops.getClass();
			}
			checkExceptions(expected, caught);
		}
		// Tests --------------------------------------------------
		/*Manage the meals of his/her restaurant, which includes creating, 
		 *editing and deleting them. A deleted meal will be not 
		 * available to order, but it still stored in the system for older orders.
		 * 
		 *Vamos a editar un plato
		 */
		@Test
		public void driverEdit() throws ParseException {
			Object testingData[][] = {
					
				{// Editar un plato de mi restaurante propio
					"manager1", 151, "Prueba",null},
				{// Editar un plato de un restaurante siendo manager, pero no su propietario
					"manager2", 151, "Prueba", IllegalArgumentException.class},
				{// Editar un plato siendo usuario
					"user1", 151, "Prueba", IllegalArgumentException.class}
			};
			
			for (int i = 0; i < testingData.length; i++)
				templateEdit((String) testingData[i][0], (int) testingData[i][1],(String) testingData[i][2],(Class<?>) testingData[i][3]);
		}
		protected void templateEdit(String username, int mealId, String title, Class<?> expected) throws ParseException {
			
			Class<?> caught = null;
			
			try {
				authenticate(username); // Iniciamos sesión con el usuario
				Meal s;
				Meal r= mealService.findOne(mealId);
				r.setTitle(title);
				s = mealService.save(r);
				Assert.isTrue(s!=null);
				Assert.isTrue(s.getTitle().equals(title));

				unauthenticate();
			} catch (Throwable oops) {
				caught = oops.getClass();
			}
			checkExceptions(expected, caught);
		}
		// Tests --------------------------------------------------
			/*Manage the meals of his/her restaurant, which includes creating, 
			 *editing and deleting them. A deleted meal will be not 
			 * available to order, but it still stored in the system for older orders.
			 * 
			 *Vamos a deshabilitar un plato
			 */
		@Test
		public void driverDisabled() throws ParseException {
			Object testingData[][] = {
					
				{// Deshabilitar plato siendo manager1
					"manager1", 151, null},
				{// Deshabilitar plato que no ha creado el manager 2
					"manager2", 151, IllegalArgumentException.class},
				
				{// Deshabilitar plato siendo un usuario
					"user1", 151, IllegalArgumentException.class},
				{//Deshabilitar plato siendo usuario sin autenticar
					null, 151, IllegalArgumentException.class},
			};
			for (int i = 0; i < testingData.length; i++)
				templateDisabled((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
		}
		

		protected void templateDisabled(String username, int mealId, Class<?> expected) throws ParseException {
		
			Class<?> caught = null;
			
			try {
				authenticate(username); // Iniciamos sesión con el usuario
				Meal r= mealService.findOne(mealId);
				mealService.disable(r);
				Meal s =  mealService.findOne(mealId);
				Assert.isTrue(s!=null);
				Assert.isTrue(s.getErased());

				unauthenticate();
			} catch (Throwable oops) {
				caught = oops.getClass();
			}
			checkExceptions(expected, caught);
		}


}
