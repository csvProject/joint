import { CsvexportModule } from './csvexport.module';

describe('CsvexportModule', () => {
  let csvexportModule: CsvexportModule;

  beforeEach(() => {
    csvexportModule = new CsvexportModule();
  });

  it('should create an instance', () => {
    expect(csvexportModule).toBeTruthy();
  });
});
