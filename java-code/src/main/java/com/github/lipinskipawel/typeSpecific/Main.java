package com.github.lipinskipawel.typeSpecific;

final class Main {
    public static void main(String[] args) {
        final var service = new PriceService(() -> 2d);

        final var price1 = service.computePrice(Movie.Type.REGULAR, 2);
        final var price2 = service.computePrice(Movie.Type.NEW_RELEASE, 2);
        final var price3 = service.computePrice(Movie.Type.CHILDREN, 2);

        System.out.println(price1);
        System.out.println(price2);
        System.out.println(price3);
    }
}
