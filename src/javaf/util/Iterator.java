
package javaf.util;

import java.util.NoSuchElementException;
import java.util.NoSuchElementException;

public interface Iterator
{
    public abstract boolean hasNext();
    public abstract Object next() throws NoSuchElementException;
    public abstract void remove() throws RuntimeException,IllegalStateException;
}
