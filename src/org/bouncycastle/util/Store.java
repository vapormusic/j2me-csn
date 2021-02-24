package org.bouncycastle.util;

import javaf.util.Collection;

public interface Store
{
    Collection getMatches(Selector selector)
        throws StoreException;
}
