package core;

public class VoidObject {

    private VoidObject(){ }

    public static VoidObject instance(){
        return new VoidObject();
    }

}
