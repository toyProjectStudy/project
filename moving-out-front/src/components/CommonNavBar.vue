<template>
  <div class ="position common-nav-bar">
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-brand href="#">Movinig-Out</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">Link</b-nav-item>
          <b-nav-item href="#" disabled>Disabled</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ms-auto">
          <div class="input-group">
            <b-nav-form>
              <b-input-group prepend="@">
                <b-form-input placeholder="Username"></b-form-input>
              </b-input-group>
            </b-nav-form>
          </div>
          <b-nav-item-dropdown text="Lang" right>
            <b-dropdown-item href="#">EN</b-dropdown-item>
            <b-dropdown-item href="#">ES</b-dropdown-item>
            <b-dropdown-item href="#">RU</b-dropdown-item>
            <b-dropdown-item href="#">FA</b-dropdown-item>
          </b-nav-item-dropdown>

          <b-nav-item-dropdown right>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <em>User</em>
            </template>
            <b-dropdown-item href="#">Profile</b-dropdown-item>
            <b-dropdown-item href="#">Sign Out</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script lang ="ts">
import Vue from "vue";
export default Vue.extend({
  name: "CommonNavBar"
});
</script>

<style>
/*.position{*/
/*  !*position: relative;*!*/
/*  top:10%;*/
/*}*/

/*.common-nav-bar{*/
/*  text-align: center;*/
/*  font-size:1em;*/

/*}*/

.common-nav-form{
  display: inline-block;
}

.selector-for-some-widget {
  box-sizing: content-box;
}
</style>
