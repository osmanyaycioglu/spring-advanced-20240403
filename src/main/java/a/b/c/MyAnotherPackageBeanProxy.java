package a.b.c;


public class MyAnotherPackageBeanProxy  extends MyAnotherPackageBean{

    private final MyAnotherPackageBean myAnotherPackageBean;

    public MyAnotherPackageBeanProxy(final MyAnotherPackageBean myAnotherPackageBeanParam) {
        myAnotherPackageBean = myAnotherPackageBeanParam;
    }

    @Override
    public String hello(String s){
        // Begin Transaction
        try {
            // Call AOP Code
            String helloLoc = myAnotherPackageBean.hello(s);
            // Call AOP Code
            // Commit
            return helloLoc;

        } catch (Exception exp) {
            // Rollback
            exp.printStackTrace();
            throw exp;
        }
    }

}
