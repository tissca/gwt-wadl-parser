package cu.tissca.x901.wad;

import com.google.common.base.Predicates;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;
import cu.tissca.x901.wad.model.MethodDescriptor;
import cu.tissca.x901.wad.model.ParamDescriptor;
import cu.tissca.x901.wad.xmlutils.NsHelper;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by avd on 2016-06-17.
 */
public class GwtTest_MethodParser_Method extends GWTTestCase {

    private static String SAMPLE_METHOD =
            "                <ns2:method xmlns:ns2=\"http://wadl.dev.java.net/2009/02\"\n" +
                    "                        id=\"getIdsOfWorklogsModifiedSince\"\n" +
                    "                        name=\"GET\">\n" +
                    "                    <ns2:doc><![CDATA[Returns worklogs id and update time of worklogs that was updated since given time.\n" +
                    " The returns set of worklogs is limited to 1000 elements.\n" +
                    " This API will not return worklogs updated during last minute.]]></ns2:doc>\n" +
                    "                    <ns2:request>\n" +
                    "                        <ns2:param\n" +
                    "                                name=\"since\" style=\"query\" type=\"xs:long\"\n" +
                    "                                default=\"0\"\n" +
                    "                                xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n" +
                    "                            <ns2:doc>\n" +
                    "                                <![CDATA[a date time in unix timestamp format since when updated worklogs will be returned.]]></ns2:doc>\n" +
                    "                        </ns2:param>\n" +
                    "                    </ns2:request>\n" +
                    "                    <ns2:response\n" +
                    "                            status=\"200\">\n" +
                    "                        <ns2:doc><![CDATA[a set of worklogs id and update time.]]></ns2:doc>\n" +
                    "                        <ns2:representation\n" +
                    "                                mediaType=\"application/json\">\n" +
                    "                            <ns2:doc>\n" +
                    "                                <ns3:p\n" +
                    "                                        xmlns:ns3=\"http://www.w3.org/1999/xhtml\">\n" +
                    "                                    <ns3:h6>Example</ns3:h6>\n" +
                    "                                    <ns3:pre>\n" +
                    "                                        <ns3:code>\n" +
                    "                                            {\"values\":[{\"worklogId\":103,\"updatedTime\":1438013671562},{\"worklogId\":104,\"updatedTime\":1438013672165},{\"worklogId\":105,\"updatedTime\":1438013693136}],\"since\":1438013671562,\"until\":1438013693136,\"self\":\"http://www.example.com/jira/worklog/updated?since=1438013671136\",\"nextPage\":\"http://www.example.com/jira/worklog/updated/updated?since=1438013671136&amp;since=1438013693136\",\"lastPage\":true}\n" +
                    "                                        </ns3:code>\n" +
                    "                                    </ns3:pre>\n" +
                    "                                </ns3:p>\n" +
                    "                            </ns2:doc>\n" +
                    "                            <ns2:doc><![CDATA[Returns a JSON representation of the worklog changes.]]></ns2:doc>\n" +
                    "                            <ns2:doc>\n" +
                    "                                <ns3:p\n" +
                    "                                        xmlns:ns3=\"http://www.w3.org/1999/xhtml\">\n" +
                    "                                    <ns3:h6>Schema</ns3:h6>\n" +
                    "                                    <ns3:pre>\n" +
                    "                                        <ns3:code>\n" +
                    "                                            {\"id\":\"https://docs.atlassian.com/jira/REST/schema/worklog-changed-since#\",\"title\":\"Worklog\n" +
                    "                                            Changed\n" +
                    "                                            Since\",\"type\":\"object\",\"properties\":{\"values\":{\"type\":\"array\",\"items\":{\"title\":\"Worklog\n" +
                    "                                            Change\",\"type\":\"object\",\"properties\":{\"worklogId\":{\"type\":\"integer\"},\"updatedTime\":{\"type\":\"integer\"}},\"additionalProperties\":false}},\"since\":{\"type\":\"integer\"},\"until\":{\"type\":\"integer\"},\"isLastPage\":{\"type\":\"boolean\"},\"self\":{\"type\":\"string\",\"format\":\"uri\"},\"nextPage\":{\"type\":\"string\",\"format\":\"uri\"}},\"additionalProperties\":false,\"required\":[\"isLastPage\"]}\n" +
                    "                                        </ns3:code>\n" +
                    "                                    </ns3:pre>\n" +
                    "                                </ns3:p>\n" +
                    "                            </ns2:doc>\n" +
                    "                        </ns2:representation>\n" +
                    "                    </ns2:response>\n" +
                    "                </ns2:method>";





    @Test
    public void test_parseMethod() throws MalformedWadlException {
        MethodParser parser = new MethodParser("ns2");
        Document document = XMLParser.parse(SAMPLE_METHOD);
        MethodDescriptor method = parser.parseMethod(document.getDocumentElement());
        assertEquals(method.getId(), "getIdsOfWorklogsModifiedSince");
        assertEquals(method.getName(), "GET");
        assertNotNull(method.getRequestDescriptor());
        assertNotNull(method.getResponseDescriptor());
        assertTrue(method.getDocs().get(0).getElement().toString().contains("Returns worklogs"));
    }

    @Override
    public String getModuleName() {
        return "cu.tissca.x901.wad.WadlParserTest";
    }
}