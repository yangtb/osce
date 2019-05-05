package com.osce.service.system.email;

import com.osce.api.system.email.PfEmailService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class PfEmailServiceImpl implements PfEmailService {

    @Override
    public boolean sendEmail() {
        return false;
    }
}
