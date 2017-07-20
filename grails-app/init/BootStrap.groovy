class BootStrap {

    def init = { servletContext ->

        def OR = """

     OOOOOOOOO                                                RRRRRRRRRRRRRRRRR                                                                                            d::::::d
   OO:::::::::OO                                              R::::::::::::::::R                                                                                           d::::::d
 OO:::::::::::::OO                                            R::::::RRRRRR:::::R                                                                                          d::::::d
O:::::::OOO:::::::O                                           RR:::::R     R:::::R                                                                                         d:::::d
O::::::O   O::::::Onnnn  nnnnnnnn        eeeeeeeeeeee           R::::R     R:::::R    eeeeeeeeeeee        cccccccccccccccc   ooooooooooo   rrrrr   rrrrrrrrr       ddddddddd:::::d
O:::::O     O:::::On:::nn::::::::nn    ee::::::::::::ee         R::::R     R:::::R  ee::::::::::::ee    cc:::::::::::::::c oo:::::::::::oo r::::rrr:::::::::r    dd::::::::::::::d
O:::::O     O:::::On::::::::::::::nn  e::::::eeeee:::::ee       R::::RRRRRR:::::R  e::::::eeeee:::::ee c:::::::::::::::::co:::::::::::::::or:::::::::::::::::r  d::::::::::::::::d
O:::::O     O:::::Onn:::::::::::::::ne::::::e     e:::::e       R:::::::::::::RR  e::::::e     e:::::ec:::::::cccccc:::::co:::::ooooo:::::orr::::::rrrrr::::::rd:::::::ddddd:::::d
O:::::O     O:::::O  n:::::nnnn:::::ne:::::::eeeee::::::e       R::::RRRRRR:::::R e:::::::eeeee::::::ec::::::c     ccccccco::::o     o::::o r:::::r     r:::::rd::::::d    d:::::d
O:::::O     O:::::O  n::::n    n::::ne:::::::::::::::::e        R::::R     R:::::Re:::::::::::::::::e c:::::c             o::::o     o::::o r:::::r     rrrrrrrd:::::d     d:::::d
O:::::O     O:::::O  n::::n    n::::ne::::::eeeeeeeeeee         R::::R     R:::::Re::::::eeeeeeeeeee  c:::::c             o::::o     o::::o r:::::r            d:::::d     d:::::d
O::::::O   O::::::O  n::::n    n::::ne:::::::e                  R::::R     R:::::Re:::::::e           c::::::c     ccccccco::::o     o::::o r:::::r            d:::::d     d:::::d
O:::::::OOO:::::::O  n::::n    n::::ne::::::::e               RR:::::R     R:::::Re::::::::e          c:::::::cccccc:::::co:::::ooooo:::::o r:::::r            d::::::ddddd::::::dd
 OO:::::::::::::OO   n::::n    n::::n e::::::::eeeeeeee       R::::::R     R:::::R e::::::::eeeeeeee   c:::::::::::::::::co:::::::::::::::o r:::::r             d:::::::::::::::::d
   OO:::::::::OO     n::::n    n::::n  ee:::::::::::::e       R::::::R     R:::::R  ee:::::::::::::e    cc:::::::::::::::c oo:::::::::::oo  r:::::r              d:::::::::ddd::::d
     OOOOOOOOO       nnnnnn    nnnnnn    eeeeeeeeeeeeee       RRRRRRRR     RRRRRRR    eeeeeeeeeeeeee      cccccccccccccccc   ooooooooooo    rrrrrrr               ddddddddd   ddddd
""";
        println OR;
    }
    def destroy = {
    }
}
