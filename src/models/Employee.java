/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author tiago
 */
public class Employee extends BaseEntity{
    private String birthDate;
    private String education;
    private String nationality;
    private String citizenship;
    private String maritalStatus;
    private Float salary;

    public Employee() {
        super();
    }

    public Employee(String name, String address, String nif, String nib, Activity activity, Set<Contact> contacts, String birthDate, String education, String nationality, String citizenship, String maritalStatus, Float salary) {
        super(name, address, nif, nib, activity, contacts);
        this.birthDate = birthDate;
        this.education = education;
        this.nationality = nationality;
        this.citizenship = citizenship;
        this.maritalStatus = maritalStatus;
        this.salary = salary;
    }
    
    public Employee(Employee e){
        super(e);
        this.birthDate = e.getBirthDate();
        this.education = e.getEducation();
        this.nationality = e.getNationality();
        this.citizenship = e.getCitizenship();
        this.maritalStatus = e.getMaritalStatus();
        this.salary = e.getSalary();
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEducation() {
        return education;
    }

    public String getNationality() {
        return nationality;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public Float getSalary() {
        return salary;
    }
    
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    
    public void setEducation(String education) {
        this.education = education;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    
    @Override
    public Employee clone(){
        return new Employee(this);
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", ");
        sb.append(birthDate);
        sb.append(", ");
        sb.append(education);
        sb.append(", ");
        sb.append(nationality);
        sb.append(", ");
        sb.append(citizenship);
        sb.append(", ");
        sb.append(maritalStatus);
        sb.append(", ");
        sb.append(salary);
        return sb.toString();
    }
    
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass() ) return false;
       
        Employee e = (Employee) o;
        
        return (super.equals(o)
                && this.birthDate.equals(e.getBirthDate())
                && this.education.equals(e.getEducation())
                && this.nationality.equals(e.getNationality())
                && this.citizenship.equals(e.getCitizenship())
                && this.maritalStatus.equals(e.getMaritalStatus())
                && this.salary == e.getSalary());
    }
}
