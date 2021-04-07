package nwsl.springboot.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * K.Saito memo
 * Spring Securityを利用するための設定クラス
 * ログイン処理でのパラメータ、画面遷移、認証処理でのデータアクセス先を設定 
 * 
 * パスワードはコンソール画面にでてくるものを使う 暫定
 * using generated security password: XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 *
 **/

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService; 
	
	/**
	 * 認可設定を無視するリスエスト設定
	 * 静的リソース(imageとかcss・・)を認可対象から除外
	 */
	@Override
	public void configure(WebSecurity websecurity) throws Exception{
		websecurity.ignoring()
				   .antMatchers("/resource/**");
	}

	/**
	 * 認証・認可の情報を設定 
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.authorizeRequests()
			.antMatchers("/login").permitAll()	// ログインformで全てのユーザーがアクセス可能
			.anyRequest().authenticated();		// 全てのURLリクエストは認証されているユーザーしかアクセスできないようにするる
		
		http.formLogin()						// フォーム認証を有効にする
			.defaultSuccessUrl("/todo");		// ログイン成功時の遷移先
			
	}
	
	/**
	 * 認証用パスワードをハッシュ化して扱う
	 * Passwordをハッシュ化にする時にBCryptPasswordEncoderを返す
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(); 
		
		// 以下ハッシュ値 確認用
		String password = "1234";
		String digest = bCryptPasswordEncoder.encode(password);
		System.out.println("ハッシュ値 : " + digest);
		
		return new BCryptPasswordEncoder();
	}
	/**
	 *　認証時に利用するデータソースを定義する設定メソッド
	 */
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		// UserDetailsServiceを設定してDaoAuthenticationProviderを有効化
		//                                           　　　　　↓で作成のエンコードしてを設定してハッシュ化
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}

}
