package org.bouncycastle.cms;

import java.util.Enumeration;
import java.util.Hashtable;
import javaf.util.Map;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.CMSAttributes;

/**
 * Default authenticated attributes generator.
 */
public class DefaultAuthenticatedAttributeTableGenerator
    implements CMSAttributeTableGenerator
{
    private final Hashtable table;

    /**
     * Initialise to use all defaults
     */
    public DefaultAuthenticatedAttributeTableGenerator()
    {
        table = new Hashtable();
    }

    /**
     * Initialise with some extra attributes or overrides.
     *
     * @param attributeTable initial attribute table to use.
     */
    public DefaultAuthenticatedAttributeTableGenerator(
        AttributeTable attributeTable)
    {
        if (attributeTable != null)
        {
            table = attributeTable.toHashtable();
        }
        else
        {
            table = new Hashtable();
        }
    }

    /**
     * Create a standard attribute table from the passed in parameters - this will
     * normally include contentType and messageDigest. If the constructor
     * using an AttributeTable was used, entries in it for contentType and
     * messageDigest will override the generated ones.
     *
     * @param parameters source parameters for table generation.
     *
     * @return a filled in Hashtable of attributes.
     */
    protected Hashtable createStandardAttributeTable(
        Map parameters)
    {
        Hashtable std = new Hashtable();

        for (Enumeration en = table.keys(); en.hasMoreElements();)
        {
            Object key = en.nextElement();

            std.put(key, table.get(key));
        }

        if (!std.containsKey(CMSAttributes.contentType))
        {
            ASN1ObjectIdentifier contentType = ASN1ObjectIdentifier.getInstance(
                parameters.get(CMSAttributeTableGenerator.CONTENT_TYPE));
            Attribute attr = new Attribute(CMSAttributes.contentType,
                new DERSet(contentType));
            std.put(attr.getAttrType(), attr);
        }

        if (!std.containsKey(CMSAttributes.messageDigest))
        {
            byte[] messageDigest = (byte[])parameters.get(
                CMSAttributeTableGenerator.DIGEST);
            Attribute attr = new Attribute(CMSAttributes.messageDigest,
                new DERSet(new DEROctetString(messageDigest)));
            std.put(attr.getAttrType(), attr);
        }

        return std;
    }

    /**
     * @param parameters source parameters
     * @return the populated attribute table
     */
    public AttributeTable getAttributes(Map parameters)
    {
        return new AttributeTable(createStandardAttributeTable(parameters));
    }
}
