/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import model.Department;
import model.Doctor;
import repo.DepartmentRepository;
import repo.DoctorRepository;
import tools.DepartmentInputValidation;
import tools.DoctorInputValidation;

/**
 *
 * @author dang khoa
 */
public class HospitaltBLL {

    DepartmentRepository deptList;
    DoctorRepository docList;

    public HospitaltBLL() throws IOException, ClassNotFoundException {
        deptList = new DepartmentRepository();
        docList = new DoctorRepository();
        deptList.readFromFile();
        docList.readFromFile();
    }

    public void addDoctor() throws IOException {
        boolean check = true;
        while (check) {
            String id = docList.inputIdDoc();
            if (docList.containsKey(id) == true) {
                System.out.println("Invalid input");
            } else {
                String name = docList.inputNameDoc(id);
                String sex = docList.inputSexDoc(id);
                String address = docList.inputAddresDoc(id);
                String deptID = deptList.inputDeptValid();
                String createDate = DoctorInputValidation.getDateCurrent();
                String lastUpdate = "NULL";
                docList.put(id, new Doctor(id, name, sex, address, deptID, createDate, lastUpdate));
                check = false;
            }
        }

    }

    public void updateDoctor() throws IOException {
        boolean check = true;
        while (check) {
            String id = docList.inputIdDoc();
            if (docList.containsKey(id) == true) {
                docList.get(id).setName(docList.inputNameDoc(id));
                docList.get(id).setSex(docList.inputSexDoc(id));
                docList.get(id).setAddress(docList.inputAddresDoc(id));
                docList.get(id).setDepartmentID(this.inputDeptValidUpdate(id));
                docList.get(id).setLastUpdate(DoctorInputValidation.getDateCurrent());
                check = false;
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    public String inputDeptValidUpdate(String id) {
        String dept = "";
        boolean check = true;
        while (check) {
            dept = DoctorInputValidation.updateString("Input department id", docList.get(id).getDepartmentID());
            if (deptList.containsKey(dept) == true) {
                check = false;
            } else {
                System.out.println("Invalid input");
            }
        }
        return dept;
    }

    public void deleteDoctor() throws IOException {
        boolean check = true;
        while (check) {
            String id = docList.inputIdDoc();
            if (docList.containsKey(id) == true) {
                docList.remove(id);
                check = false;
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    public void addDepartment() throws IOException {
        boolean check = true;
        while (check) {
            String id = deptList.inputIdDept();
            if (deptList.containsKey(id) == true) {
                System.out.println("Invalid input");
            } else {
                String name = deptList.inputNamedept(id);
                String createDate = DepartmentInputValidation.getDateCurrent();
                String lastUpdate = "NULL";
                deptList.put(id, new Department(id, name, createDate, lastUpdate));
                check = false;
            }
        }
    }

    public void updateDepartment() throws IOException {
        boolean check = true;
        while (check) {
            String id = deptList.inputIdDept();
            if (deptList.containsKey(id) == true) {
                deptList.get(id).setName(deptList.inputNamedept(id));
                deptList.get(id).setLastUpdateDate(DepartmentInputValidation.getDateCurrent());
                check = false;
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    // DELETE DEPARTMENT
    public void deleteDepartment() throws IOException {
        boolean check = true;
        while (check) {
            String id = deptList.inputIdDept();
            if (deptList.containsKey(id) == true) {
                if (this.checkDeptIdInDoctorList(id) == true) {
                    System.out.println("Invalid input");
                } else {
                    deptList.remove(id);
                    check = false;
                }
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    // CHECK DEPARTMENT IN DOCTOR LIST
    public boolean checkDeptIdInDoctorList(String id) {
        boolean check = false;
        for (Doctor value : docList.values()) {
            if (value.getDepartmentID().equalsIgnoreCase(id) == true) {
                check = true;
            }
        }
        return check;
    }

    public void showAllDataDepartment() {
        System.out.println("List of department in hospital");
        System.out.println("ID=====Name=====Create date=====Last update date");
        for (Department value : deptList.sortDept().values()) {
            value.showInfo();
        }
        System.out.println("Number of departments:" + deptList.size());
    }

    public void showAllDataDoctor() {
        System.out.println("List of doctor in hospital");
        System.out.println("ID=====Name=====Sex=====Address=====Deparment");
        for (Doctor value : docList.sortDoc().values()) {
            System.out.println(value.showInfo() + deptList.get(value.getDepartmentID()).getName());
        }
        System.out.println("Number of doctors:" + docList.size());

    }

    public void searchDepartmentByName() {
        Map<String, Department> listDeptSearch = new TreeMap<>();
        String name = DoctorInputValidation.getString("Input department name");
        for (Department value : deptList.values()) {
            if (value.getName().toLowerCase().contains(name.toLowerCase()) == true) {
                listDeptSearch.put(value.getDepartmentId(), value);
            }
        }
        if (listDeptSearch.isEmpty() == true) {
            System.out.println("Nothing here");
        } else {
            System.out.println("List of department in hospital whose names contain '" + name + "'");
            System.out.println("ID=====Name=====Create date=====Last update date");
            for (Department listsearch : listDeptSearch.values()) {
                listsearch.showInfo();
            }
            System.out.println("Number of departments:" + listDeptSearch.size());
        }

    }

    // SEARCH NAME DOCTOR
    public void searchDoctorByName() {
        Map<String, Doctor> listDocSearch = new TreeMap<>();
        String name = DoctorInputValidation.getString("Input doctor name");
        for (Doctor value : docList.values()) {
            if (value.getName().toUpperCase().contains(name.toUpperCase()) == true) {
                listDocSearch.put(value.getDoctorID(), value);
            }
        }
        if (listDocSearch.isEmpty() == true) {
            System.out.println("Nothing here");
        } else {
            System.out.println("List of doctor in hospital whose names contain '" + name + "'");
            System.out.println("ID=====Name=====Sex=====Address=====Deparment");
            for (Doctor listsearch : listDocSearch.values()) {
                System.out.println(listsearch.showInfo() + deptList.get(listsearch.getDepartmentID()).getName());
            }
            System.out.println("Number of doctors:" + listDocSearch.size());
        }
    }

    public void storeAllDateToFile() throws IOException {
        docList.writeToFile();
        deptList.writeToFile();
        System.out.println("Store file successfully");

    }

}
