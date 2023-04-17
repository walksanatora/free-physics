package net.walksanator.freephys.mixin;

import net.walksanator.freephys.ExampleMod;
import net.diebuddies.util.HttpRequest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Mixin(HttpRequest.class)
public class MixinHttpRequest {
	@Inject(at = @At("RETURN"), method = "get(Ljava/lang/String;)Ljava/lang/String;",cancellable = true)
	private void phys$get(String urlToRead, CallbackInfo info) {
		if (urlToRead.contains("verify.minecraftphysicsmod.com")) {
			Map<String,String> opts = FreePhysics.getParamsFromUrl(urlToRead);
			String code = opts.get("code");
			if (code != null) {
				info.cancel();
				info.setReturnValue(code+"verified");
				FreePhysics.LOGGER.info("Prevented another usless verification check");
			}
		}
	}
}
/*
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class Example {
    public static void main(String[] args) throws Exception {
        String url = "http://verify.minecraftphysicsmod.com/verify?code=ash18239&name=walksanator";
        Map<String, String> params = getParamsFromUrl(url);
        String code = params.get("code");
        String name = params.get("name");
        System.out.println("code = " + code); // prints "code = ash18239"
        System.out.println("name = " + name); // prints "name = walksanator"
    }

    private static Map<String, String> getParamsFromUrl(String url) throws Exception {
        Map<String, String> params = new HashMap<>();
        URL parsedUrl = new URL(url);
        String query = parsedUrl.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] parts = pair.split("=");
            String key = URLDecoder.decode(parts[0], "UTF-8");
            String value = URLDecoder.decode(parts[1], "UTF-8");
            params.put(key, value);
        }
        return params;
    }
}

 */