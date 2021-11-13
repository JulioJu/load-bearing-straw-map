<template>
  <!-- START added by JulioJu -->
  <footer id="footer" class="footer">
    <section class="footer__hr"></section>
    <FooterMain />
  </footer>
  <!-- END added by JulioJu -->
</template>

<script lang="ts" src="./jhi-footer.component.ts"></script>

<script>
// START added by JulioJu
import FooterMain from './FooterMain.vue';

export default {
  components: { FooterMain },
};
// END added by JulioJu
</script>

<style scoped lang="scss">
/** START added by JulioJu */
$grey-white2: #d6d6d6;
$black-light: #353d47;
$blue: #3077b4;

.footer {
  color: $grey-white2;
  background-color: $black-light;
  width: 100%;
  position: absolute;
  left: 0;

  &__hr {
    width: 100%;
    height: 0.2rem;
    background-color: $blue;
  }
}
/** END added by JulioJu */
</style>
