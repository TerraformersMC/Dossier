package com.terraformersmc.dossier;

public interface DossierProvider {
	Dossiers createDossiers();

	boolean isEnabled();
}
