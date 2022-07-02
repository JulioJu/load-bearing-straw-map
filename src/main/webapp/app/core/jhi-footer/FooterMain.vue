<template>
  <section class="footer-main container">
    <a class="footer-main__nebraksa-logo" target="_blank" href="http://nebraskaconstruction.fr/">
      <img loading="lazy" src="/content/images/logo_nebraska.jpg" alt="Logo Nebraska Construction" />
    </a>
    <section class="footer-main__nebraska-text">
      Site web financé par
      <a target="_blank" href="http://nebraskaconstruction.fr/"> Nebraska&nbsp;Construction </a>
      <br />
      Association pour le développement de la paille porteuse
    </section>
    <a class="footer-main__cdc-logo" target="_blank" href="https://www.caissedesdepots.fr/">
      <img loading="lazy" src="/content/images/logo_cdc_large.svg" alt="Logo caissedesdepots" />
    </a>
    <section class="footer-main__cdc-text">
      Cofinancé par la
      <br />
      <a target="_blank" href="https://www.caissedesdepots.fr/">Caisse des dépôts et consignation</a>
    </section>
    <a class="footer-main__rfcp-logo" target="_blank" href="https://www.rfcp.fr">
      <img loading="lazy" src="/content/images/logo_rfcp.png" alt="Logo RFCP" />
    </a>
    <a class="footer-main__github-logo" href="https://github.com/JulioJu/load-bearing-straw-map">
      <img loading="lazy" src="/content/images/GitHub.svg" alt="Logo GitHub" class="mb-3" />
      <div>Code source (licence MIT)</div>
    </a>
    <div class="footer-main__legal">
      <RouterLink to="/legal">Mentions légales, Traitement des données, Cookies, CGU, Limitation de responsabilité </RouterLink>
    </div>
  </section>
</template>

<style scoped lang="scss">
$bp-400: 400px;
$bp-576: 576px;
$bp-768: 768px;
$bp-992: 992px;
$bp-1200: 1200px;

$fontSizeLegal: 0.8rem;
$elem-height: 5rem;
$text-max-width: 17rem;

a,
a:hover {
  color: white;
}

.footer-main {
  display: grid;
  grid-template-areas:
    'footer-main__nebraksa-logo'
    'footer-main__nebraska-text'
    'footer-main__cdc-logo'
    'footer-main__cdc-text'
    'footer-main__github-logo '
    'footer-main__rfcp-logo'
    'footer-main__legal';
  grid-gap: 1rem;
  justify-content: center;
  justify-items: center;
  padding: 3.1875rem 0 1rem 0;
  margin-top: 1.5rem;
  text-align: center;

  @media (min-width: #{$bp-768}) {
    grid-template-areas:
      'footer-main__nebraksa-logo  footer-main__cdc-logo'
      'footer-main__nebraska-text  footer-main__cdc-text'
      'footer-main__rfcp-logo      footer-main__github-logo'
      'footer-main__legal          footer-main__legal';
    grid-template-rows: $elem-height auto auto auto;
    grid-template-columns: repeat(2, 1r);
  }

  @media (min-width: #{$bp-1200}) {
    grid-template-areas:
      'footer-main__nebraksa-logo  footer-main__cdc-logo footer-main__rfcp-logo    footer-main__github-logo'
      'footer-main__nebraska-text  footer-main__cdc-text footer-main__rfcp-logo    footer-main__github-logo'
      'footer-main__legal          footer-main__legal         footer-main__legal  footer-main__legal';
    grid-template-rows: $elem-height + 0.25rem auto auto;
    grid-template-columns: repeat(4, 1r);
    grid-gap: 0rem;
    justify-content: space-between;
  }

  &__nebraksa-logo {
    grid-area: footer-main__nebraksa-logo;
    img {
      height: $elem-height;
    }
  }

  &__nebraska-text {
    grid-area: footer-main__nebraska-text;
    max-width: $text-max-width;
    @media (min-width: $bp-1200) {
      margin-bottom: 0;
    }
  }

  &__cdc-logo {
    grid-area: footer-main__cdc-logo;
    img {
      max-height: $elem-height;
    }
  }

  &__cdc-text {
    grid-area: footer-main__cdc-text;
  }

  &__rfcp-logo {
    grid-area: footer-main__rfcp-logo;
    @media (min-width: $bp-1200) {
      img {
        max-height: #{$elem-height} + 2rem;
      }
    }
  }

  &__github-logo {
    grid-area: footer-main__github-logo;
    display: flex;
    flex-direction: column;
    img {
      height: $elem-height;
    }
  }

  &__legal {
    display: grid;
    grid-area: footer-main__legal;
    grid-auto-flow: row;
    grid-gap: 2rem;
    justify-content: center;
    font-size: $fontSizeLegal;
    text-align: center;
    max-width: $text-max-width;
    @media (min-width: $bp-576) {
      grid-auto-flow: column;
      text-align: unset;
    }
  }
}
</style>
