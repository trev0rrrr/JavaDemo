package trev0r.mybatis.domain;

public class Dto {
    private int id;
    private String data;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String toString(){
        return "Dto [id: "+id+",data: "+data+"]";
    }
}
