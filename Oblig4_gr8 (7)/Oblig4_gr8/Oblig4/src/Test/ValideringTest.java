package Test;

import org.junit.Assert;
import org.junit.Test;
import validering.Validator;

public class ValideringTest {
	
	private final Validator validator = new Validator();
	
	@Test
	public void testValiderFornavn() {
		Assert.assertTrue(validator.validerFornavn("Vebjørn Vårdal-æÆØÅ"));
		Assert.assertFalse(validator.validerFornavn("vebjørn Vårdal"));
		Assert.assertFalse(validator.validerFornavn("V"));
		Assert.assertFalse(validator.validerFornavn("Vebjørn Vårdalooooooo"));
		Assert.assertFalse(validator.validerFornavn(""));
		Assert.assertFalse(validator.validerFornavn("          "));
		Assert.assertFalse(validator.validerFornavn("123456789"));
		Assert.assertFalse(validator.validerFornavn(null));
	}
	
	@Test
	public void testValiderEtternavn() {
		Assert.assertTrue(validator.validerEtternavn("VebjørnVårdal-æÆØÅ"));
		Assert.assertFalse(validator.validerEtternavn("Vebjørn Vårdal-æÆØÅ"));
		Assert.assertFalse(validator.validerEtternavn("vebjørn Vårdal"));
		Assert.assertFalse(validator.validerEtternavn("V"));
		Assert.assertFalse(validator.validerEtternavn("Vebjørn Vårdalooooooo"));
		Assert.assertFalse(validator.validerEtternavn(""));
		Assert.assertFalse(validator.validerEtternavn("          "));
		Assert.assertFalse(validator.validerEtternavn("123456789"));
		Assert.assertFalse(validator.validerEtternavn(null));
	}
	
	@Test
	public void testValiderMobil() {
		Assert.assertFalse(validator.validerMobil("1234567"));
		Assert.assertTrue(validator.validerMobil("12345678"));
		Assert.assertFalse(validator.validerMobil("123456789"));
		Assert.assertFalse(validator.validerMobil("abcd5678"));
	}
	
	@Test
	public void testValiderPassord() {
		Assert.assertTrue(validator.validerPassord("abcd5678"));
		Assert.assertFalse(validator.validerPassord("abc123"));
		Assert.assertFalse(validator.validerPassord("aaaabbbbccccdddd1111222233334444aaaabbbbccccdddd1111222233334444a"));
		Assert.assertFalse(validator.validerPassord(""));
		Assert.assertFalse(validator.validerPassord("        "));
		Assert.assertFalse(validator.validerPassord(null));
	}
	
	@Test
	public void testValiderPassordRepetert() {
		Assert.assertTrue(validator.validerPassordRepetert("12345678","12345678"));
		Assert.assertTrue(validator.validerPassordRepetert("",""));
		Assert.assertTrue(validator.validerPassordRepetert(null,null));
		Assert.assertFalse(validator.validerPassordRepetert("12345678a","12345678A"));
		Assert.assertFalse(validator.validerPassordRepetert("12345678","123456789"));
		Assert.assertFalse(validator.validerPassordRepetert("12345678",null));
		Assert.assertFalse(validator.validerPassordRepetert(null,"12345678"));
		Assert.assertFalse(validator.validerPassordRepetert("",null));
		Assert.assertFalse(validator.validerPassordRepetert(null,""));

	}
	
	@Test
	public void testValiderKjonn() {
		Assert.assertTrue(validator.validerKjonn("mann"));
		Assert.assertTrue(validator.validerKjonn("kvinne"));
		Assert.assertFalse(validator.validerKjonn(null));
	}

}