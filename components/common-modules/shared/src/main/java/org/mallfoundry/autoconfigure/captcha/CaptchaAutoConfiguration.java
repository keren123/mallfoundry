/*
 * Copyright (C) 2019-2020 the original author or authors.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package org.mallfoundry.autoconfigure.captcha;

import org.mallfoundry.captcha.CaptchaRepository;
import org.mallfoundry.captcha.SmsCaptchaProvider;
import org.mallfoundry.sms.MessageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CaptchaProperties.class)
public class CaptchaAutoConfiguration {

    @ConditionalOnProperty(prefix = "mallfoundry.captcha", name = "type", havingValue = "sms")
    @Bean
    public SmsCaptchaProvider smsCaptchaService(CaptchaProperties properties, MessageService messageService,
                                                CaptchaRepository captchaRepository) {
        var sms = properties.getSms();
        var provider = new SmsCaptchaProvider(messageService, captchaRepository);
        provider.setSignature(sms.getSignature());
        provider.setTemplate(sms.getTemplate());
        provider.setExpires(sms.getExpires());
        provider.setIntervals(sms.getIntervals());
        return provider;
    }
}