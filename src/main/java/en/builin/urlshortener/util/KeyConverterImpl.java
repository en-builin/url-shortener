package en.builin.urlshortener.util;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class KeyConverterImpl implements KeyConverter {

    @Override
    public long keyToId(String key) {
        return bytesToLong(Base64.getDecoder().decode(key + "="));
    }

    @Override
    public String idToKey(long id) {
        String base64 = Base64.getEncoder().encodeToString(longToBytes(id));
        return base64.substring(0, base64.length()-1);
    }

    private byte[] longToBytes(long l) {
        byte[] result = new byte[Long.BYTES];
        for (int i = Long.BYTES - 1; i >= 0; i--) {
            result[i] = (byte)(l & 0xFF);
            l >>= Byte.SIZE;
        }
        return result;
    }

    private long bytesToLong(final byte[] b) {
        long result = 0;
        for (int i = 0; i < Long.BYTES; i++) {
            result <<= Byte.SIZE;
            result |= (b[i] & 0xFF);
        }
        return result;
    }
}
