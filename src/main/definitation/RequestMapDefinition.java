package main.definitation;

public class RequestMapDefinition {

    private String urlPattern;

    private String className;

    private boolean redirect;

    private String method;

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
