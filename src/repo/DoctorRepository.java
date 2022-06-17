/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import model.Doctor;
import tools.DoctorInputValidation;

/**
 *
 * @author dang khoa
 */
public class DoctorRepository extends HashMap<String, Doctor> {

    public String inputIdDoc() {
        String id = "";
        boolean check = true;
        while (check) {
            id = DoctorInputValidation.getString("Input doctor id");
            if (DoctorInputValidation.checkDoctorId(id) == true) {
                check = false;
            }
        }
        return id;
    }

    public String inputNameDoc(String id) {
        String name = "";
        boolean check = true;
        if (this.containsKey(id) == true) {
            while (check) {
                name = DoctorInputValidation.updateString("Input doctor name", this.get(id).getName());
                if (DoctorInputValidation.checkDoctorName(name) == true) {
                    check = false;
                }
            }
        } else {
            while (check) {
                name = DoctorInputValidation.getString("Input doctor name");
                if (DoctorInputValidation.checkDoctorName(name) == true) {

                    check = false;
                }
            }
        }
        return name;
    }

    public String inputSexDoc(String id) {
        String sex = "";
        boolean check = true;
        if (this.containsKey(id) == true) {
            while (check) {
                sex = DoctorInputValidation.updateString("Input sex", this.get(id).isSex());
                if (DoctorInputValidation.checkDoctorInputSex(sex) == true) {
                    check = false;
                }
            }
        } else {
            while (check) {

                sex = DoctorInputValidation.getString("Input sex");
                if (DoctorInputValidation.checkDoctorInputSex(sex) == true) {
                    check = false;
                }
            }
        }
        return sex;
    }

    public String inputAddresDoc(String id) {
        String address = "";
        boolean check = true;
        if (this.containsKey(id) == true) {
            while (check) {
                address = DoctorInputValidation.updateString("Input address", this.get(id).getAddress());
                if (DoctorInputValidation.checkAddress(address) == true) {
                    check = false;
                }
            }
        } else {
            while (check) {
                address = DoctorInputValidation.updateString("Input address", "NULL");
                if (DoctorInputValidation.checkAddress(address) == true) {
                    check = false;
                }
            }
        }
        return address;
    }

    public Map<String, Doctor> sortDoc() {
        Map<String, Doctor> sort = new TreeMap<String, Doctor>(this);
        return sort;
    }

    public void readFromFile() throws IOException {
        Doctor doctor;
        File file = new File("doctor.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            doctor = new Doctor();
            StringTokenizer stk = new StringTokenizer(line, ",", false);
            while (stk.hasMoreElements()) {
                doctor.setDoctorID(stk.nextToken());
                doctor.setName(stk.nextToken());
                doctor.setSex(stk.nextToken());
                doctor.setAddress(stk.nextToken());
                doctor.setDepartmentID(stk.nextToken());
                doctor.setCreateDate(stk.nextToken());
                doctor.setLastUpdate(stk.nextToken());
            }
            this.put(doctor.getDoctorID(), doctor);
        }
        reader.close();
    }

    public void writeToFile() throws IOException {
        File file = new File("doctor.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        for (Doctor value : this.sortDoc().values()) {
            fw.write(value.toString() + "\n");
        }
        fw.close();
    }

}
