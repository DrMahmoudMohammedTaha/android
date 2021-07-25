if (typeof window.jivo_magic_var == "undefined"){var jivo_magic_var = 123321;
window.jivo_config={"widget_id":"kemtRC0lX7","site_id":108790,"widget_color":"#3A89E8","widget_font_color":"light","widget_padding":"50","widget_height":"33","widget_orientation":"right","font_size":"14","font_family":"Arial","font_type":"normal","locale":"en_US","show_rate_form":1,"hide_ad":1,"secure":0,"contacts_ask":0,"style_string":"jivo_shadow jivo_rounded_corners jivo_gradient jivo_3d_effect jivo_border jivo_3px_border","chat_mode":1?"online":"offline","options":0,"hide_offline":1,"vox_login":"jivosite@chat.jivosite.voximplant.com","avatar_url":"\/\/s3-eu-west-1.amazonaws.com\/jivo-userdata","online_widget_label":"Chat with us, we are online!","offline_widget_label":"Send us a message","offline_form_text":"Leave your message in the form below, and we will receive it by e-mail!","base_url":"\/\/code.jivosite.com","static_host":"cdn.jivosite.com","comet":{"host":"node12.jivosite.com"}};
(function(){
var RETRIES_LIMIT = 3, retriesCount = 0, timerId;
var isMobile = /iPhone|iPad|iPod|Android|Windows Phone/i.test(navigator.userAgent);
var timeoutTime = isMobile? 8000 : 6000;
var onerrorCb = function(type) {
    clearTimeout(timerId);
    if (++retriesCount >= RETRIES_LIMIT) {return;}
    try {
        localStorage.setItem("jv_store_cdn_resolve_time", (new Date().getTime() + 120000).toString());
        localStorage.setItem("jv_store_cdn_unavailable", type);
    } catch (e) {}
    jivoLoad();
};
var getUrl = function() {
    var timestamp = null;
    try {
        timestamp = parseInt(localStorage.getItem("jv_store_cdn_resolve_time"), 10);
    } catch (e) {}
    var cdnUnavailable = timestamp && timestamp >= (new Date()).getTime();
    return (cdnUnavailable? window.jivo_config.base_url : "//cdn.jivosite.com") + "/js/iframe_loader.js?rand=1476356953";
};
function jivoLoad() {
    var sc = document.getElementsByTagName("script");
    var iA = sc[0];
    var se = document.createElement("script");
    se.type = "text/javascript"; se.async = true; se.charset="UTF-8";
    se.onerror = function(){ onerrorCb("error")};
    iA.parentNode.insertBefore(se, iA).src = getUrl();
    timerId = setTimeout(function () {
        if (typeof window.jivo_init === "undefined") {
            iA.parentNode.removeChild(se);
            onerrorCb("timeout");
        }
    }, timeoutTime);
}
jivoLoad();
})();};