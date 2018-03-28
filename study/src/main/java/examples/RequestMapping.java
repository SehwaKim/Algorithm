package examples;

public class RequestMapping {
    private String path;
    private String servletClassName;
    private Servlet serlvet;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServletClassName() {
        return servletClassName;
    }

    public void setServletClassName(String servletClassName) {
        this.servletClassName = servletClassName;
    }

    public Servlet getSerlvet() {
        return serlvet;
    }

    public void setSerlvet(Servlet serlvet) {
        this.serlvet = serlvet;
    }
}