package utilitys;

import java.io.Serial;
import java.util.Random;

public class MTRandom extends Random {
    @Serial
    private static final long serialVersionUID = -515082678588212038L;

    private final static int UPPER_MASK = 0x80000000;
    private final static int LOWER_MASK = 0x7fffffff;

    private final static int N = 624;
    private final static int M = 397;
    private final static int[] MAGIC = {0x0, 0x9908b0df};
    private final static int MAGIC_FACTOR1 = 1812433253;
    private final static int MAGIC_FACTOR2 = 1664525;
    private final static int MAGIC_FACTOR3 = 1566083941;
    private final static int MAGIC_MASK1 = 0x9d2c5680;
    private final static int MAGIC_MASK2 = 0xefc60000;
    private final static int MAGIC_SEED = 19650218;
    private final static long DEFAULT_SEED = 5489L;

    private transient int[] mt;
    private transient int mti;
    private transient boolean compatible = false;

    private transient int[] transientBuffer;

    public MTRandom() {
        this.setSeed(System.currentTimeMillis());
    }

    public MTRandom(boolean compatible) {
        super(0L);
        this.compatible = compatible;
        setSeed(this.compatible ? DEFAULT_SEED : System.currentTimeMillis());
    }

    public MTRandom(long seed) {
        super(seed);
        this.setSeed(seed);
    }

    public MTRandom(int[] buf) {
        super(0L);
        setSeed(buf);
    }

    public MTRandom(byte[] buf) {
        super(0L);
        setSeed(buf);
    }

    public static int[] pack(byte[] buf) {
        int k, blen = buf.length, ilen = ((buf.length + 3) >>> 2);
        int[] ibuf = new int[ilen];
        for (int n = 0; n < ilen; n++) {
            int m = (n + 1) << 2;

            if (m > blen) {
                m = blen;
            }

            for (k = buf[--m] & 0xff; (m & 0x3) != 0; k = (k << 8) | buf[--m] & 0xff) ;
            ibuf[n] = k;
        }
        return ibuf;
    }

    private void setSeed(int seed) {
        if (mt == null) {
            mt = new int[N];
        }

        mt[0] = seed;

        for (mti = 1; mti < N; mti++) {
            mt[mti] = (MAGIC_FACTOR1 * (mt[mti - 1] ^ (mt[mti - 1] >>> 30)) + mti);
        }
    }

    public final synchronized void setSeed(int[] buf) {
        int length = buf.length;

        if (length == 0) {
            throw new IllegalArgumentException("seed buffer may not be empty");
        }

        int i = 1, j = 0, k = (Math.max(N, length));
        setSeed(MAGIC_SEED);

        for (; k > 0; k--) {
            mt[i] = (mt[i] ^ ((mt[i - 1] ^ (mt[i - 1] >>> 30)) * MAGIC_FACTOR2))
                    + buf[j] + j;
            i++;
            j++;
            if (i >= N) {
                mt[0] = mt[N - 1];
                i = 1;
            }

            if (j >= length) {
                j = 0;
            }
        }

        for (k = N - 1; k > 0; k--) {
            mt[i] = (mt[i] ^ ((mt[i - 1] ^ (mt[i - 1] >>> 30)) * MAGIC_FACTOR3)) - i;
            i++;
            if (i >= N) {
                mt[0] = mt[N - 1];
                i = 1;
            }
        }

        mt[0] = UPPER_MASK;
    }

    public final synchronized void setSeed(long seed) {
        if (compatible) {
            setSeed((int) seed);
        } else {
            if (transientBuffer == null) {
                transientBuffer = new int[2];
            }

            transientBuffer[0] = (int) seed;
            transientBuffer[1] = (int) (seed >>> 32);
            setSeed(transientBuffer);
        }
    }

    public final void setSeed(byte[] buf) {
        setSeed(pack(buf));
    }

    protected final synchronized int next(int bits) {
        int y, kk;
        if (mti >= N) {
            for (kk = 0; kk < N - M; kk++) {
                y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
                mt[kk] = mt[kk + M] ^ (y >>> 1) ^ MAGIC[y & 0x1];
            }

            for (; kk < N - 1; kk++) {
                y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
                mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ MAGIC[y & 0x1];
            }

            y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
            mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ MAGIC[y & 0x1];

            mti = 0;
        }

        y = mt[mti++];

        y ^= (y >>> 11);
        y ^= (y << 7) & MAGIC_MASK1;
        y ^= (y << 15) & MAGIC_MASK2;
        y ^= (y >>> 18);

        return (y >>> (32 - bits));
    }
}
