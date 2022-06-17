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
import model.Department;
import tools.DepartmentInputValidation;
import tools.DoctorInputValidation;

/**
 *
 * @author dang khoa
 */
public class DepartmentRepository extends HashMap<String, Department> {

    public String inputIdDept() {
        String id = "";
        boolean check = true;
        while (check) {
            id = DoctorInputValidation.getString("Input department id");
            if (DepartmentInputValidation.checkDepartmentId(id) == true) {
                check = false;
            }
        }
        return id;
    }

    public String inputNamedept(String id) {
        String name = "";
        boolean check = true;
        if (this.containsKey(id) == true) {
            while (check) {
                name = DoctorInputValidation.updateString("Input department name", this.get(id).getName());
                if (DepartmentInputValidation.checkDepartmentName(name) == true) {
                    check = false;
                }
            }
        } else {
            while (check) {
                name = DoctorInputValidation.getString("Input department name");
                if (DepartmentInputValidation.checkDepartmentName(name) == true) {
                    check = false;
                }
            }
        }
        return name;
    }

    public String inputDeptValid() {
        String dept = "";
        boolean check = true;
        while (check) {
            dept = this.inputIdDept();
            if (this.containsKey(dept) == true) {
                check = false;
            } else {
                System.out.println("Invalid input");
            }
        }
        return dept;
    }

    public Map<String, Department> sortDept() {
        Map<String, Department> sort = new TreeMap<String, Department>(this);
        return sort;
    }

    public void readFromFile() throws IOException {
        Department dep;
        File file = new File("department.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            dep = new Department();
            StringTokenizer stk = new StringTokenizer(line, ",", false);
            while (stk.hasMoreTokens()) {
                dep.setDepartmentId(stk.nextToken());
                dep.setName(stk.nextToken());
                dep.setCreateDate(stk.nextToken());
                dep.setLastUpdateDate(stk.nextToken());
            }
            this.put(dep.getDepartmentId(), dep);
        }
        reader.close();

    }

    public void writeToFile() throws IOException {
        File file = new File("department.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        for (Department value : this.sortDept().values()) {
            fw.write(value.toString() + "\n");
        }
        fw.close();
    }
}
