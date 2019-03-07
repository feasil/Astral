package fr.feasil.astral.profil;

import java.util.List;

public interface ProfilManager<T extends Profil> {

	public T newEmptyProfil();
	public void saveProfil(T profil);
	
	public List<T> getAllProfils(); 
	
}
