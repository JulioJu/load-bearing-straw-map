export interface ILoadBearingStrawMap {
  id?: number;
  name?: string;
  longitude?: number;
  latitude?: number;
}

export class LoadBearingStrawMap implements ILoadBearingStrawMap {
  constructor(public id?: number, public name?: string, public longitude?: number, public latitude?: number) {}
}
