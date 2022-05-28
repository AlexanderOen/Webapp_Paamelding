package validering;

public class Validator {
	
	public Validator() {}
	
	public boolean validerFornavn(String fornavn) {
		return (fornavn != null && !fornavn.isBlank() && fornavn.matches("[A-ZÆØÅ]{1}[a-zA-ZæøåÆØÅ -]{1,19}"));
	}
	
	public boolean validerEtternavn(String etternavn) {
		return (etternavn != null && !etternavn.isBlank() && etternavn.matches("[A-ZÆØÅ]{1}[a-zA-ZæøåÆØÅ-]{1,19}"));
	}

	public boolean validerMobil(String mobil) {	
		int check = -1;
		try {
			check = Integer.parseInt(mobil);
		}
		catch(NumberFormatException e) {}
		
		return (mobil.length() == 8 && check > -1);
	}
	
	public boolean validerPassord(String passord) {	
		return !(passord == null || passord.isBlank() || passord.length() < 8 || passord.length() > 64);
	}
	
	public boolean validerPassordRepetert(String passordRepetert, String passord) {
		return passordRepetert == null ? passord == null : passordRepetert.equals(passord);
	}
	
	public boolean validerKjonn(String kjonn) {
		return kjonn != null;
	}

}