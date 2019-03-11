package fr.feasil.astral.graphic.datamissing;


public interface DataMissingListener {
	public void dataMissingSuivant();
	public void dataMissingPrecedant();
	public void dataMissingTerminer(boolean isChanged);
}
