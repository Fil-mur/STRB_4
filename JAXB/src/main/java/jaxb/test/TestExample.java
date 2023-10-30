package jaxb.test;

import jaxb.model.Department;
import jaxb.model.Employee;
import jaxb.model.Organization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TestExample {

    private static final String XML_FILE = "dept-info.xml";

    public static void main(String[] args) {

        Employee emp1 = new Employee("E01", "Tom", null);
        Employee emp2 = new Employee("E02", "Mary", "E01");
        Employee emp3 = new Employee("E03", "John", null);

        List<Employee> list = new ArrayList<Employee>();
        list.add(emp1);
        list.add(emp2);
        list.add(emp3);

        Department dept1 = new Department("D01", "ACCOUNTING", "NEW YORK");
        List<Department> list1 = new ArrayList<Department>();
        list1.add(dept1);

        dept1.setEmployees(list);

        List<Employee> employees2 = new ArrayList<>();
        employees2.add(emp3);

        Department dept2 = new Department("D02", "HR", "LOS ANGELES");
        dept2.setEmployees(employees2);

        Organization organization = new Organization();
        List<Department> departments = new ArrayList<>();
        departments.add(dept1);
        departments.add(dept2);
        organization.setDepartments(departments);
        try {
            //сообщает JAXB, какой класс должен быть сериализован в XML
            // и какой класс будет использоваться для десериализации XML в объекты Java.
            JAXBContext context = JAXBContext.newInstance(Organization.class);

            // Создаем Marshaller для сериализации
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);


            File outFile = new File(XML_FILE);
            // Сериализация организации
            m.marshal(organization, outFile);
            System.err.println("Write to file: " + outFile.getAbsolutePath());

            // Создаем Unmarshaller для десериализации
            Unmarshaller um = context.createUnmarshaller();

            // Десериализация из файла
            Organization orgFromFile = (Organization) um.unmarshal(new FileReader(XML_FILE));
            List<Department> depts = orgFromFile.getDepartments();

            for (Department d : depts) {
                System.out.println("Department: " + d.getDeptName());
                for (Employee emp : d.getEmployees()) {
                    System.out.println("Employee: " + emp.getEmpName());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}