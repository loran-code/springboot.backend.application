package nl.akker.springboot.backend.application.service;

import nl.akker.springboot.backend.application.model.ReturnObject;
import nl.akker.springboot.backend.application.model.dbmodels.WorkOrder;

public interface AdditionalService {

    ReturnObject addAdditionalLabor(WorkOrder workorder);
}
