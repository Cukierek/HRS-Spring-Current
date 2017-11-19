package pl.com.bottega.hrs.model.commands;

public class FireEmployeeCommand implements Command  {

    private int empNo;

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }
}