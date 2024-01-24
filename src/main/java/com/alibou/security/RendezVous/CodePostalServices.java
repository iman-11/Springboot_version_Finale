package com.alibou.security.RendezVous;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
@Service
public class CodePostalServices {
    private static final Map<String, String> postalCodes = new HashMap<>();

    static {
        postalCodes.put("Agadir", "80000");
        postalCodes.put("Al Hoceïma", "32000");
        postalCodes.put("Al Kelaa des Sraghna", "43000");
        postalCodes.put("Al Azizal", "22000");
        postalCodes.put("Ben Slimane", "13000");
        postalCodes.put("Béni Mellal", "23000");
        postalCodes.put("Boujdour", "71000");
        postalCodes.put("Boulemane", "33000");
        postalCodes.put("Casablanca", "20000");
        postalCodes.put("Chefchaouen", "91000");
        postalCodes.put("Chichaoua", "41000");
        postalCodes.put("Dakhla", "73000");
        postalCodes.put("Efrane", "53000");
        postalCodes.put("El Jadida", "24000");
        postalCodes.put("Errachidia", "52000");
        postalCodes.put("Es Semara", "72000");
        postalCodes.put("Essaouirra", "44000");
        postalCodes.put("Fès", "30000");
        postalCodes.put("Figuig", "61000");
        postalCodes.put("Guelmim", "81000");
        postalCodes.put("Kénitra", "14000");
        postalCodes.put("Khémisset", "15000");
        postalCodes.put("Khénifra", "54000");
        postalCodes.put("Rabat", "10000");
        postalCodes.put("Salé", "11000");
        postalCodes.put("Tanger", "90000");
        postalCodes.put("Taza", "35000");
        postalCodes.put("Témara", "12000");
        postalCodes.put("Tétouan", "93000");
    }

    public String getPostalCode(String city) {
        return postalCodes.get(city);
    }

    public Map<String, String> getAllPostalCodes() {
        return postalCodes;
    }
}
