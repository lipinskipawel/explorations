import java.util.function.BiFunction;

class Movie {
    enum Type {
        REGULAR(PriceService::computeRegularPrice),
        NEW_RELEASE(PriceService::computeNewReleasePrice),
        CHILDREN(PriceService::computeChildrenPrice);

        public final BiFunction<PriceService, Integer, Integer> priceAlgo;

        Type(BiFunction<PriceService, Integer, Integer> priceAlgo) {
            this.priceAlgo = priceAlgo;
        }
    }

    private final Type type;

    Movie(final Type type) {
        this.type = type;
    }

    public static void main(String[] args) {
        final var service = new PriceService(() -> 2d);

        final var price1 = service.computePrice(Type.REGULAR, 2);
        final var price2 = service.computePrice(Type.NEW_RELEASE, 2);
        final var price3 = service.computePrice(Type.CHILDREN, 2);

        System.out.println(price1);
        System.out.println(price2);
        System.out.println(price3);
    }
}

interface PriceRepo {
    double getFactor();
}

class PriceService {

    private final PriceRepo priceRepo;

    PriceService(final PriceRepo priceRepo) {
        this.priceRepo = priceRepo;
    }

    int computeRegularPrice(final int days) {
        return days + 1;
    }

    int computeNewReleasePrice(final int days) {
        return (int) this.priceRepo.getFactor() * days;
    }

    int computeChildrenPrice(final int days) {
        return 5;
    }

    int computePrice(final Movie.Type type, final int days) {
        return type.priceAlgo.apply(this, days);
    }
}
