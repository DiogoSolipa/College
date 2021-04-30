Public Class Test
{
    public static void main(String[] args)
    {   
        System.out.println("System java version: "+System.getProperty("java.version"));
        System.out.println("System java vendor: "+System.getProperty("java.vendor"));
        System.out.println("System classpath: "+System.getProperty("java.class.path"));

        System.exit(0);
    }
}