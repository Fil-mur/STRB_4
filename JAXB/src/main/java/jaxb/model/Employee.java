package jaxb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee") // класс Employee представляет корневой элемент в XML-документе
@XmlAccessorType(XmlAccessType.FIELD) // устанавливает способ доступа к полям класса: FIELD - непосредственно поля класса
public class Employee {

    private String empNo;
    private String empName;
    private String managerNo;

    public Employee() {

    }

    public Employee(String empNo, String empName, String managerNo) {
        this.empNo = empNo;
        this.empName = empName;
        this.managerNo = managerNo;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getManagerNo() {
        return managerNo;
    }

    public void setManager(String managerNo) {
        this.managerNo = managerNo;
    }

}