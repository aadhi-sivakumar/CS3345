public class ListStackNode 
{
    public ListStackNode next;
    private final double data;

    public ListStackNode(double data)
    {
        this.next = null;
        this.data = data;
    }

    public double getData()
    {
        return this.data;
    }
}