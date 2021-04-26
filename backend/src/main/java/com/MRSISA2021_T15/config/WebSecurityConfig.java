package com.MRSISA2021_T15.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.MRSISA2021_T15.security.RestAuthenticationEntryPoint;
import com.MRSISA2021_T15.security.TokenAuthenticationFilter;
import com.MRSISA2021_T15.service.CustomUserDetailsService;
import com.MRSISA2021_T15.utils.TokenUtils;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService)
		.passwordEncoder(passwordEncoder());
	}

	@Autowired
	private TokenUtils tokenUtils;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
			.authorizeRequests().antMatchers("/auth/login").permitAll()
				.antMatchers("/auth/createFirstSystemAdmin").permitAll()
				.antMatchers("/registration/registerPatient").permitAll()
				.antMatchers("/registration/confirmAccount").permitAll()
				.antMatchers("/registration/registerSystemAdministrator").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/registration/registerPharmacyAdministrator").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/registration/registerSupplier").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/registration/registerDermatologist").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/medicine/addMedicine").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/medicine/{medicineId}/delete").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/medicine/getMedicineList").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/medicine/all").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/medicine/{medicineId}/findById").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/medicine/{string}/findByString").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/medicine/{medicineId}/update").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/pharmacy/registerPharmacy").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/pharmacy/getPharmacyAdminsWithNoPharmacy").hasAuthority("ROLE_SYSTEM_ADMIN")
				.antMatchers("/suppliers/updateSupplierData").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/suppliers/getSupplierData").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/suppliers/updatePassword").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/suppliers/getMedicineSupply").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/suppliers/writeOffer").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/suppliers/getOrders").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/suppliers/getOrderByName/{orderName}").hasAuthority("ROLE_SUPPLIER")
				.antMatchers("/appointment_creation/pharmacist").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/appointment_creation/dermatologist").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/appointment_creation/dermatologistPredefined/{appointmentId}").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/calendar/calendarPharmacist").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/calendar/calendarDermatologist/id={dermatologistId}pharmacy={pharmacyId}").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/calendar/calendarDermatologist").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/calendar/calendarDermatologistPredefined").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/calendar/calendarDermatologistPredefined/pharmacy={pId}").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/dermatologist/add").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/dermatologist/{dermatologistId}/delete").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/dermatologist/all").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/dermatologist/{dermatologistId}/findById").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/dermatologist/{string}/findByString").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/dermatologist/update").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/dermatologist/get").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/patients/searchPatient/{patientUsername}").hasAuthority("ROLE_PATIENT")
				.antMatchers("/patients/changeDataShow/{patientUsername}").hasAuthority("ROLE_PATIENT")
				.antMatchers("/patients/changeData/{patientUsername}").hasAuthority("ROLE_PATIENT")
				.antMatchers("/patient-search/searchPatientsPharmacist").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/patient-search/searchPatientsDermatologist").hasAuthority("ROLE_DERMATOLOGIST")
				.antMatchers("/pharmacist/add").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/pharmacist/{pharmacistId}/delete").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/pharmacist/all").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/pharmacist/get").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/pharmacist/update").hasAuthority("ROLE_PHARMACIST")
				.antMatchers("/pharmacyAdmin/{pharmacyAdminId}/findById").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/pharmacyAdmin/{pharmacyAdminId}/update").hasAuthority("ROLE_PHARMACY_ADMIN")
				.antMatchers("/complaint/getAllDermatologists").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/getAllPharmacist").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/getAllPharmacy").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/getComplaints/{patientUsername}").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/checkDermatologist/{patientUsername}").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/checkPharmacist/{patientUsername}").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/checkPharmacy/{patientUsername}").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/addComplaintToDermatologist/{patientUsername}").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/addComplaintToPharmacist/{patientUsername}").hasAuthority("ROLE_PATIENT")
				.antMatchers("/complaint/addComplaintToPharmacy/{patientUsername}").hasAuthority("ROLE_PATIENT")
			.anyRequest().authenticated().and()
			.cors().and()
			.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, customUserDetailsService), BasicAuthenticationFilter.class);
		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js");
	}

}
