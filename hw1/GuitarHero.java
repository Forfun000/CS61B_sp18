import synthesizer.GuitarString;

public class GuitarHero {
    private static final double CONCERT = 440.0;

    public static void main(String[] args) {
        /* create guitar strings array including 37 GuitarString */
        GuitarString[] stringsBuffer = new GuitarString[36];
        for (int i = 0; i < stringsBuffer.length; i++) {
            stringsBuffer[i] = new GuitarString(CONCERT * Math.pow(2, (i - 24) / 12));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            /*
             * Use the following 37 keys to represent the keyboard, from lowest note to
             * highest note:
             */
            /* "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' " */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                switch (key) {
                    case 'q':
                        stringsBuffer[0].pluck();
                        break;
                    case '2':
                        stringsBuffer[1].pluck();
                        break;
                    case 'w':
                        stringsBuffer[2].pluck();
                        break;
                    case 'e':
                        stringsBuffer[3].pluck();
                        break;
                    case '4':
                        stringsBuffer[4].pluck();
                        break;
                    case 'r':
                        stringsBuffer[5].pluck();
                        break;
                    case '5':
                        stringsBuffer[6].pluck();
                    default:
                        break;
                }
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (GuitarString guitarString : stringsBuffer) {
                sample += guitarString.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString guitarString : stringsBuffer) {
                guitarString.tic();
            }
        }
    }
}
