package br.com.arthouseserv.controller;

import br.com.arthouseserv.dto.LeadDTO;
import br.com.arthouseserv.form.LeadDescontoForm;
import br.com.arthouseserv.services.lead.LeadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
@CrossOrigin
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @GetMapping
    public ResponseEntity<List<LeadDTO>> getAllLeads() {
        List<LeadDTO> leads = leadService.getAllLeads();
        return ResponseEntity.ok(leads);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeadDTO> getLeadById(@PathVariable Long id) {
        return leadService.getLeadById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LeadDTO> createLead(@RequestBody LeadDescontoForm leadDescontoForm) {
        LeadDTO createdLead = leadService.createLead(leadDescontoForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLead);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeadDTO> updateLead(@PathVariable Long id, @RequestBody LeadDTO leadDTO) {
        try {
            LeadDTO updatedLead = leadService.updateLead(id, leadDTO);
            return ResponseEntity.ok(updatedLead);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLead(@PathVariable Long id) {
        leadService.deleteLead(id);
        return ResponseEntity.noContent().build();
    }
}
