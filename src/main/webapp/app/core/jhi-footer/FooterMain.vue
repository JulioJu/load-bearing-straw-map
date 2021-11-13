<template>
  <section class="footer-main container">
    <a class="footer-main__logo" target="_blank" href="http://nebraskaconstruction.fr/">
      <img loading="lazy" src="/content/images/logo_nebraska.jpg" alt="Logo Nebraska Construction" />
    </a>
    <a class="footer-main__nav" target="_blank" href="https://www.rfcp.fr">
      <img loading="lazy" src="/content/images/logo_rfcp.png" alt="Logo RFCP" />
    </a>
    <a class="footer-main__externallinks" href="https://github.com/JulioJu/load-bearing-straw-map">
      <img loading="lazy" src="/content/images/GitHub.svg" alt="Logo GitHub" class="mb-3" />
      <div>Code source (licence MIT)</div>
    </a>
    <section class="footer-main__nebraska-text">
      Site web financé par
      <a target="_blank" href="http://nebraskaconstruction.fr/"> Nebraska&nbsp;Construction </a>
      <br />
      Association pour le développement de la paille porteuse
    </section>
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
$elem-height: 2.5rem;

a,
a:hover {
  color: white;
}

.footer-main {
  display: grid;
  grid-template-areas:
    'footer-main__logo          '
    'footer-main__externallinks '
    'footer-main__nav    '
    'footer-main__nebraska-text   '
    'footer-main__legal         ';
  grid-gap: 1rem;
  justify-content: center;
  justify-items: center;
  padding: 3.1875rem 0 1rem 0;
  margin-top: 1.5rem;

  @media (min-width: #{$bp-768}) {
    grid-template-areas:
      'footer-main__logo        footer-main__externallinks'
      'footer-main__nav  footer-main__nav'
      'footer-main__nebraska-text    footer-main__nebraska-text'
      'footer-main__legal       footer-main__legal';
  }

  @media (min-width: #{$bp-1200}) {
    grid-template-areas:
      'footer-main__logo           footer-main__nav  footer-main__externallinks'
      'footer-main__nebraska-text   footer-main__nav  footer-main__externallinks'
      'footer-main__legal          footer-main__legal       footer-main__legal';
    grid-template-rows: ($elem-height + 0.25rem) auto auto;
    grid-column-gap: 2rem;
    justify-content: space-between;
  }

  &__logo {
    grid-area: footer-main__logo;
    margin-bottom: 1.5rem;
    @media (min-width: $bp-768) {
      margin-right: 3rem;
      margin-bottom: 0;
    }
    @media (min-width: $bp-1200) {
      margin-right: unset;
    }

    img {
      height: $elem-height;
    }
  }

  &__externallinks {
    grid-area: footer-main__externallinks;
    display: flex;
    flex-direction: column;
    img {
      height: $elem-height;
    }
  }

  &__nav {
    grid-area: footer-main__nav;
    margin-top: 0;
    margin-top: 3.5rem;
    margin-bottom: 3.5rem;
    @media (min-width: $bp-1200) {
      margin-top: 0;
    }
  }

  &__nebraska-text {
    grid-area: footer-main__nebraska-text;
    max-width: 17rem;
    margin-bottom: 2rem;
    text-align: center;
    @media (min-width: $bp-1200) {
      margin-bottom: 0;
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
    @media (min-width: $bp-576) {
      grid-auto-flow: column;
      text-align: unset;
    }
  }
}
</style>
