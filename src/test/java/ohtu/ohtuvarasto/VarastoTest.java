package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisellaTilavuudellaLuotuVarastoTilavuusNollataan() {
        varasto = new Varasto(-1);

        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void alkusaldollaJaValidillaTilavuudellaLuodutuVarastoOikeaTilavuus() {
        varasto = new Varasto(10, 5);

        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void alkusaldollaJaNegatiivisellaTilavuudellaLuotuVarastoNollaTilavuus() {
        varasto = new Varasto(-1, 5);

        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisellaAlkusaldollaJaTilavuudellaLuotuVarastoNollaSaldo() {
        varasto = new Varasto(10, -1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void mahtuvallaAlkusaldollaLuotuVarastoOikeaSaldo() {
        varasto = new Varasto(10, 5);

        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liianIsollaAlkusaldollaLuotuVarastoSaldoTilavuudenKokoinen() {
        varasto = new Varasto(10, 15);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaysNegatiivisellaMaarallaEiLisaaSaldoa() {
        varasto.lisaaVarastoon(-1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysNegatiivisellaMaarallaEiPienennaVapaataTilaa() {
        varasto.lisaaVarastoon(-1);

        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLiianIsollaMaarallaLaitaaSaldonTilavuudenVerran() {
        varasto.lisaaVarastoon(12);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void NegatiivinenOttaminenPalauttaaNolla() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(-1);

        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void LiianIsoOttaminenPalauttaaKokoSaldon() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(10);

        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void varastoPalauttaaOikeanMuotoisenMerkkiJonon() {
        String odotettuMerkkijono = "saldo = 0.0, vielä tilaa 10.0";

        assertEquals(odotettuMerkkijono, varasto.toString());
    }

}