package com.paymentplatform.embeddedpayments.merchant.application;

import com.paymentplatform.embeddedpayments.auth.application.GenerateMerchantApiKeyUseCase;
import com.paymentplatform.embeddedpayments.merchant.domain.entity.Merchant;
import com.paymentplatform.embeddedpayments.merchant.domain.repository.MerchantRepository;
import com.paymentplatform.embeddedpayments.merchant.domain.services.MerchantDomainService;
import com.paymentplatform.embeddedpayments.shared.audit.AuditEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterMerchantUseCaseTest {

    @Mock
    private MerchantRepository merchantRepository;
    @Mock
    private MerchantDomainService merchantDomainService;
    @Mock
    private AuditEventService auditEventService;
    @Mock
    private GenerateMerchantApiKeyUseCase generateMerchantApiKeyUseCase;

    @InjectMocks
    private RegisterMerchantUseCase registerMerchantUseCase;

    @BeforeEach
    void setUp() {
        // Limpiar el contexto de seguridad antes de cada prueba
        SecurityContextHolder.clearContext();
    }

    @Test
    void execute_DebeRegistrarComercioYDevolverApiKey_CuandoDatosSonValidos() {
        // ==========================================
        // ARRANGE (Preparar el escenario)
        // ==========================================
        String name = "Mi Comercio Seguro";
        String email = "contacto@micomercio.com";
        String generatedApiKey = "sk_test_abc123";
        UUID merchantId = UUID.randomUUID();

        // Simulamos las entidades que devuelven los mocks
        Merchant builtMerchant = mock(Merchant.class);
        Merchant savedMerchant = mock(Merchant.class);

        when(savedMerchant.getId()).thenReturn(merchantId);

        // Configuramos el comportamiento de los servicios inyectados
        when(merchantDomainService.buildNewMerchant(name, email)).thenReturn(builtMerchant);
        when(merchantRepository.save(builtMerchant)).thenReturn(savedMerchant);
        when(generateMerchantApiKeyUseCase.execute(merchantId)).thenReturn(generatedApiKey);

        // Simulamos el contexto de seguridad (Spring Security)
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("juanes_user");
        SecurityContextHolder.setContext(securityContext);

        // ==========================================
        // ACT (Actuar sobre el método a probar)
        // ==========================================
        RegisterMerchantUseCase.RegisteredMerchant result =
                registerMerchantUseCase.execute(name, email);

        // ==========================================
        // ASSERT (Afirmar y validar los resultados)
        // ==========================================

        // 1. Verificamos que el resultado no sea nulo y contenga lo esperado
        assertNotNull(result);
        assertEquals(savedMerchant, result.merchant());
        assertEquals(generatedApiKey, result.apiKey());

        // 2. Verificamos que los métodos de los mocks fueron llamados correctamente
        verify(merchantDomainService).buildNewMerchant(name, email);
        verify(merchantRepository).save(builtMerchant);
        verify(generateMerchantApiKeyUseCase).execute(merchantId);

        // Verificamos que el evento de auditoría se registró con los parámetros correctos
        verify(auditEventService).registerMerchantCreated(eq(merchantId), eq("SELF_REGISTRATION"), eq("juanes_user"));
    }
}