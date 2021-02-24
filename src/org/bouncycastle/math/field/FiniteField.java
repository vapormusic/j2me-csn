package org.bouncycastle.math.field;

import javaf.math.BigInteger;

public interface FiniteField
{
    BigInteger getCharacteristic();

    int getDimension();
}
