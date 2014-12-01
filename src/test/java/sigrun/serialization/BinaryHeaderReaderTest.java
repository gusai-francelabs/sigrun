package sigrun.serialization;

import org.junit.Assert;
import org.junit.Test;
import sigrun.common.BinaryHeader;
import sigrun.common.DataSample;

/**
 * Created by maksenov on 01/12/14.
 */
public class BinaryHeaderReaderTest {
    private static final byte[] SERIALIZED_HEADER = new byte[]{
            0x00, 0x00, 0x00, 0x01, /* Job identification number 0x00000001 */
            0x00, 0x00, 0x00, 0x02, /* Line number 0x00000002 */
            0x00, 0x00, 0x00, 0x03, /* Reel number */
            0x00, 0x04,             /* Number of data traces per ensemble - 0x0004 */
            0x00, 0x05,             /* Number of auxiliary traces per ensemble - 0x0005 */
            0x00, 0x06,             /* Sample interval - 0x0006 */
            0x00, 0x07,             /* Sample interval in ms OFR - 0x0007 */
            0x00, 0x08,             /* Number of samples - 0x0008 */
            0x00, 0x09,             /* Number of samples OFR - 0x0009 */
            0x00, 0x01,             /* Data sample format code - 0x0001 */
            0x00, 0x02,             /* Ensemble fold 0x0002 */
            0x00, 0x07,             /* Trace sorting code - 0x0007 */
            0x7F, 0x00,             /* Vertical sum code - 0x7F00 */
            0x10, 0x00,             /* Sweep frequency at start - 0x1000 */
            0x01, 0x00,             /* Sweep frequency at end - 0x0100 */
            0x00, 0x10,             /* Sweep length - 0x0010 */
            0x00, 0x01,             /* Sweep type code - 0x0001 */
            0x20, 0x00,             /* Trace number of sweep channel 0x2000 */
            0x02, 0x00,             /* Taper length at start - 0x0200 */
            0x00, 0x20,             /* Taper length at end - 0x0020 */
            0x00, 0x02,             /* Taper type - 0x0002 */
            0x00, 0x01,             /* Correlated - 0x0001 */
            0x00, 0x02,             /* Recovered - 0x0002 */
            0x00, 0x01,             /* Amplitude recovery method - 0x0001 */
            0x00, 0x02,             /* Measurement system - 0x0002 */
            0x00, 0x01,             /* Polarity - 0x0001 */
            0x00, 0x03,             /* Vibratory - 0x0003 */
            /* Unassigned 240-byte gap */
            /* 1 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 2 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 3 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 4 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 5 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 6 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 7 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 8 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 9 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 10 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 11 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 12 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 13 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 14 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 15 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 16 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 17 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 18 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 19 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 20 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 21 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 22 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 23 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 24 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x01, 0x00,             /* SEG Y format revision number */
            0x00, 0x01,             /* Fixed length trace flag */
            0x00, 0x00,             /* Number of 3200 byte */
            /* Unassigned 94-byte gap */
            /* 1 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 2 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 3 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 4 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 5 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 6 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 7 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 8 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 9 */ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            /* 0 */ 0x00, 0x00, 0x00, 0x00
    };

    @Test
    public void testRead() {
        BinaryHeaderFormat format = BinaryHeaderFormatBuilder.
                aBinaryHeaderFormat().
                withJobIdFormat(FormatEntry.create(0, 4)).
                withSampleIntervalFormat(FormatEntry.create(16, 18)).
                withSamplesPerDataTraceFormat(FormatEntry.create(20, 22)).
                withDataSampleCodeFormat(FormatEntry.create(24, 26)).
                build();

        BinaryHeaderReader reader = new BinaryHeaderReader(format);

        BinaryHeader header = reader.read(SERIALIZED_HEADER);

        Assert.assertEquals(new Integer(1), header.getJobId());
        Assert.assertEquals((short) 6, header.getSampleInterval());
        Assert.assertEquals(DataSample.create((short) 1), header.getDataSampleCode());
    }
}
