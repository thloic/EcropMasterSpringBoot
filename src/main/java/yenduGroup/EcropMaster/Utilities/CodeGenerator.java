package yenduGroup.EcropMaster.Utilities;

import yenduGroup.EcropMaster.DTO.ParcelleDto;
import yenduGroup.EcropMaster.DTO.ProducteurDto;
import yenduGroup.EcropMaster.Entities.Producteur;

public class CodeGenerator {
    public static String generateCode(ParcelleDto parcelleDto, ProducteurDto producteurDto) {
        // Générer la section des référentiels
        StringBuilder refSection = new StringBuilder();
        String[] referentiels = parcelleDto.getReferentiel().split(",");
        for (String ref : referentiels) {
            refSection.append(ref.trim());
        }

        // Utiliser la première lettre du produit
        String produitInitial = parcelleDto.getSituation().substring(0, 1).toUpperCase();

        // Formater l'année d'adhésion
        int anneeAdhesion = producteurDto.getDateAdhesion().getYear() % 100;
        String anneeAdhesionStr = String.format("%02d", anneeAdhesion);

        // Utiliser la première lettre de la région
        String regionInitial = parcelleDto.getRegion().substring(0, 1).toUpperCase();

        // Utiliser la première lettre du village
        String villageInitial = parcelleDto.getVillage().substring(0, 1).toUpperCase();

        // Formater l'ID du producteur
        String producteurIdStr = String.format("%03d", producteurDto.getId());

        // Formater le nombre de parcelles en utilisant des lettres (A, B, C...)
        char parcelleId = (char) ('A' + parcelleDto.getNombredeParcelle() - 1);

        // Générer le code complet
        return String.format("%s-%s-%s-%s-%s-%s-%s",
                refSection.toString(), produitInitial, anneeAdhesionStr, regionInitial, villageInitial, producteurIdStr, parcelleId);
    }
}

