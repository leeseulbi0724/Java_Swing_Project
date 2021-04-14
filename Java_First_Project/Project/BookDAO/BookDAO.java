package BookDAO;

public class BookDAO {
	String bno, bookname, author, pblsh, pblshdate;
    int price;
    public String getBno() {
        return bno;
    }
    public void setBno(String bno) {
        this.bno = bno;
    }
    public String getBookname() {
        return bookname;
    }
    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
    public String getAuthro() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPblshdate() {
        return pblshdate;
    }
    public void setPblshdate(String pblshdate) {
        this.pblshdate = pblshdate;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getPblsh() {
        return pblsh;
    }
    public void setPblsh(String pblsh) {
        this.pblsh = pblsh;
    }
}
