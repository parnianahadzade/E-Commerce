package com.mftplus.ecommerce.api.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('admin')")
@RestController
@RequestMapping("/admin")
public class AdminController {

}
