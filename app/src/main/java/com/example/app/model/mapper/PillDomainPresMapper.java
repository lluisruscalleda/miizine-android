package com.example.app.model.mapper;

import com.example.app.model.Pill;
import com.example.domain.model.PillDomain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PillDomainPresMapper {

  @Inject
  public PillDomainPresMapper() {
  }

  public Pill transform(PillDomain pillDomain) {
    return Pill.create(pillDomain.getId(), pillDomain.getName(), pillDomain.getDescription(), pillDomain.getCategorie(), pillDomain.getImage());
  }

  public List<Pill> transform(Collection<PillDomain> pillDomainCollection) {
    List<Pill> pillList = new ArrayList<>(pillDomainCollection.size());
    Pill pill;
    for (PillDomain pillDomain : pillDomainCollection) {
      pill = transform(pillDomain);
      if (pill != null) {
        pillList.add(pill);
      }
    }

    return pillList;
  }

  public PillDomain transformToDomain(Pill pill) {
    return PillDomain.create(pill.getId(), pill.getName(), pill.getDescription(), pill.getCategorie(), pill.getImage());
  }

  public List<PillDomain> transformToDomain(Collection<Pill> pillCollection) {
    List<PillDomain> pillList = new ArrayList<>(pillCollection.size());
    PillDomain pillDomain;
    for (Pill pill : pillCollection) {
      pillDomain = transformToDomain(pill);
      if (pill != null) {
        pillList.add(pillDomain);
      }
    }

    return pillList;
  }
}
